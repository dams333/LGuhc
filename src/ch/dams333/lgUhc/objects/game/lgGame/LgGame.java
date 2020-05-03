package ch.dams333.lgUhc.objects.game.lgGame;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.events.events.*;
import ch.dams333.lgUhc.objects.couple.Couple;
import ch.dams333.lgUhc.objects.couple.CoupleWin;
import ch.dams333.lgUhc.objects.game.tasks.DeathTask;
import ch.dams333.lgUhc.objects.game.tasks.GameTask;
import ch.dams333.lgUhc.objects.game.tasks.Time;
import ch.dams333.lgUhc.objects.game.tasks.VoteTask;
import ch.dams333.lgUhc.objects.game.win.WinSort;
import ch.dams333.lgUhc.objects.player.LGplayer;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.RoleWin;
import ch.dams333.lgUhc.objects.roles.roles.*;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LgGame implements Listener {

    private List<Role> roles;

    public List<Player> inGamePlayers;

    private LgUhc main;

    public int border;

    private Couple couple;

    private List<String> endMSG;

    public LgGame(LgUhc lgUhc){
        this.roles = new ArrayList<>();
        this.main = lgUhc;
        main.getServer().getPluginManager().registerEvents(this, main);
        border = 1200;
        inGamePlayers = new ArrayList<>();
        this.death = new HashMap<>();
        this.deathTasks = new HashMap<>();
        endMSG = new ArrayList<>();
    }

    @EventHandler
    public void start(LGstartgameEvent e){
        main.lgPermissionManager.removeAllPerm();
        Bukkit.getWorld("world").setGameRule(GameRule.REDUCED_DEBUG_INFO, true);
        for(Player p : Bukkit.getOnlinePlayers()){
            Bukkit.broadcastMessage(ChatColor.GOLD + "Téléportation de " + p.getName());
            p.setGameMode(GameMode.SURVIVAL);
            p.setFoodLevel(20);
            p.setHealth(20);
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999, 999999, true), true);
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 999999, true), true);
            int x = main.API.random(1100, 800);
            if(main.API.random(0, 1) == 1) x = (-1)*x;
            int z = main.API.random(1100, 800);
            if(main.API.random(0, 1) == 1) z = (-1)*z;
            p.teleport(new Location(Bukkit.getWorld("world"), x, 150, z));
            main.lgPermissionManager.addPerm(p, "role");
            main.lgPermissionManager.addPerm(p, "vote");
            p.getInventory().clear();
            p.getInventory().addItem(main.API.itemStackManager.create(Material.COOKED_BEEF, 64));




            p.setLevel(10000);
            p.getInventory().addItem(main.API.itemStackManager.create(Material.ANVIL, 64));
            p.getInventory().addItem(main.API.itemStackManager.create(Material.CRAFTING_TABLE, 64));
            p.getInventory().addItem(main.API.itemStackManager.create(Material.IRON_INGOT, 4));
            p.getInventory().addItem(main.API.itemStackManager.create(Material.REDSTONE, 1));

            ItemStack helmet = new ItemStack(Material.IRON_HELMET);
            ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
            ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
            ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
            ItemStack sword = new ItemStack(Material.IRON_SWORD);
            ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
            ItemStack bow = new ItemStack(Material.BOW);

            helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
            chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
            leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
            boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
            sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
            pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 2);
            bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);

            p.getInventory().addItem(helmet);
            p.getInventory().addItem(chestplate);
            p.getInventory().addItem(leggings);
            p.getInventory().addItem(boots);
            p.getInventory().addItem(sword);
            p.getInventory().addItem(pickaxe);
            p.getInventory().addItem(bow);

            p.getInventory().addItem(main.API.itemStackManager.create(Material.COBBLESTONE, 64));
            p.getInventory().addItem(main.API.itemStackManager.create(Material.COBBLESTONE, 64));
            p.getInventory().addItem(main.API.itemStackManager.create(Material.COBBLESTONE, 64));
            p.getInventory().addItem(main.API.itemStackManager.create(Material.COBBLESTONE, 64));

            p.getInventory().addItem(main.API.itemStackManager.create(Material.ARROW, 64));
            p.getInventory().addItem(main.API.itemStackManager.create(Material.ARROW, 64));


            p.getInventory().addItem(main.API.itemStackManager.create(Material.WATER_BUCKET, 1));

            p.getInventory().addItem(main.API.itemStackManager.create(Material.GOLDEN_APPLE, 5));


        }
        main.scoreboardManager.createSB();
        Bukkit.getWorld("world").setGameRule(GameRule.NATURAL_REGENERATION, false);
        WorldBorder w = Bukkit.getWorld("world").getWorldBorder();
        w.setCenter(new Location(Bukkit.getWorld("world"), 0, 0, 0));
        w.setSize(2400);
        w.setWarningDistance(10);
        Bukkit.getWorld("world").setTime(0);
        inGamePlayers.addAll(Bukkit.getOnlinePlayers());
        GameTask gameTask = new GameTask(main);
        gameTask.runTaskTimer(main, 20, 20);
    }

    @EventHandler
    public void selectRoles(LGselectrolesEvent e){
        List<Player> toSelectRole = new ArrayList<>(inGamePlayers);
        boolean randomCouple = true;
        for(Role role : main.lgGame.getRoles()) {
            if(role.getSort() == RoleSort.CUPIDON && role.getPlayers().size() > 0) randomCouple = false;
            int needed = role.getNeededPlayers();
            List<Player> toAdd = new ArrayList<>();
            if (needed > 0) {
                while (needed > 0) {
                    int rdm = main.API.random(0, toSelectRole.size() - 1);
                    toAdd.add(toSelectRole.get(rdm));
                    toSelectRole.remove(rdm);
                    needed = needed - 1;
                }
                for (Player p : toAdd) {
                    role.addPlayer(p);
                    p.sendMessage(ChatColor.BLUE + "[Privé] Vous êtes " + ChatColor.BOLD + role.getName());
                    p.sendMessage(ChatColor.GOLD + role.getDescription());
                    main.lgPermissionManager.addPerm(p, "compo");
                    if(main.lgsSorts.contains(role.getSort())){
                        p.sendMessage(ChatColor.RED + "/lg role pour connaître la liste des loups");
                    }
                }
            }
        }
        if(randomCouple){
            List<Player> toselect = new ArrayList<>(inGamePlayers);
            Player p1 = null;
            Player p2 = null;
            int rdm = main.API.random(0, toselect.size() - 1);
            p1 = toselect.get(rdm);
            toselect.remove(p1);
            rdm = main.API.random(0, toselect.size() - 1);
            p2 = toselect.get(rdm);
            toselect.remove(p2);

            this.setCouple(p1, p2);
            p1.sendMessage(ChatColor.LIGHT_PURPLE + "Vous êtes en couple avec " + p2.getName() + ". Craftez une boussole pour le retrouver");
            p2.sendMessage(ChatColor.LIGHT_PURPLE + "Vous êtes en couple avec " + p1.getName() + ". Craftez une boussole pour le retrouver");

        }
    }

    public Map<Player, Location> death;
    private Map<Player, DeathTask> deathTasks;

    @EventHandler
    public void playerDieEvent(LGplayerdieEvent e){
        boolean hasRoles = false;
        for(Role role : this.roles){
            if(role.getPlayers().size() > 0){
                hasRoles = true;
            }
        }
        if(hasRoles) {
            Player p = e.getPlayer();
            if (isAnAncienCanRes(p)) {
                ((Rancien) this.getRole(RoleSort.ANCIEN)).hasRes(p);
                this.res(p);
            } else {
                sendToSorcierPlayerRes(p);
                savePlayerDeath(p);
            }
        }else{
            killPlayerBeforeRoles(e.getPlayer());
        }
    }

    private void killPlayerBeforeRoles(Player p) {

        p.setGameMode(GameMode.SPECTATOR);
        for(ItemStack it : p.getInventory()){
            if(it != null){
                p.getLocation().getWorld().dropItemNaturally(p.getLocation(), it);
            }
        }

        if(this.inGamePlayers.contains(p)){
            this.inGamePlayers.remove(p);
        }

        for(Player online : Bukkit.getOnlinePlayers()){
            online.playSound(online.getLocation(), Sound.ENTITY_WITHER_SPAWN, 10, 1);
        }

        Bukkit.broadcastMessage(ChatColor.RED + "====================");
        Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "Le village a perdu un de ses membres avant de connaître son rôle: " + p.getName());
        Bukkit.broadcastMessage(ChatColor.RED + "====================");

    }

    @EventHandler
    public void playerKillEvent(LGplayerkillEvent e){
        Player p = e.getPlayer();
        Player killer = e.getKiller();
        if(isAnAncienCanRes(p)){
            ((Rancien)this.getRole(RoleSort.ANCIEN)).hasRes(p);
            this.res(p);
        }else{
            sendToSorcierPlayerRes(p);
            global:
            for(Role role : this.roles){
                if(main.lgsSorts.contains(role.getSort())){
                    for(LGplayer lGplayer : role.getPlayers()){
                        if(lGplayer.getP() == killer){
                            sendToInfectPlayerRes(p);
                            break global;
                        }
                    }
                }
                if(role.getSort() == RoleSort.ENFANT){
                    if(((Renfantsauvage) role).isTransformed(killer)){
                        sendToInfectPlayerRes(p);
                        break global;
                    }
                }
                for(LGplayer lGplayer : role.getPlayers()){
                    if(lGplayer.isInfect() && lGplayer.getP() == killer){
                        sendToInfectPlayerRes(p);
                        break global;
                    }
                }
            }
            savePlayerDeath(p);
        }
    }

    public void sorciereHasRes(Player p){
        Rsorciere role = (Rsorciere) this.getRole(RoleSort.SORCIERE);
        int index = this.roles.indexOf(role);
        role.hasRes(p);
        roles.set(index, role);
    }

    public void infectHasRes(Player p){
        Rinfecteperedesloups role = (Rinfecteperedesloups) this.getRole(RoleSort.INFECT);
        int index = this.roles.indexOf(role);
        role.hasRes(p);
        roles.set(index, role);
    }

    private void savePlayerDeath(Player p){
        this.death.put(p, p.getLocation());
        p.teleport(new Location(Bukkit.getWorld("world"), 0, 150, 0));
        p.sendMessage(ChatColor.LIGHT_PURPLE + "Vous êtes mort. Veuillez ne pas vous déconnecter, vous pouvez être réssucité");
        p.setHealth(p.getMaxHealth());
        p.setFoodLevel(20);
        DeathTask deathTask = new DeathTask(main, p);
        deathTask.runTaskTimer(main, 20, 20);
        this.deathTasks.put(p, deathTask);
    }

    private void sendToSorcierPlayerRes(Player p){
        for(LGplayer lGplayer : this.getRole(RoleSort.SORCIERE).getPlayers()) {
            if(((Rsorciere)this.getRole(RoleSort.SORCIERE)).canRes(lGplayer.getP())) {
                if (lGplayer.getP() != p && !this.death.keySet().contains(lGplayer.getP())) {
                    lGplayer.getP().sendMessage(ChatColor.LIGHT_PURPLE + p.getName() + " est mort. Vous avez 30 secondes pour le sauver grâce à la commande" + ChatColor.BOLD + " /lg sauver " + p.getName());

                    Rsorciere role = (Rsorciere) getRole(RoleSort.SORCIERE);
                    int index = this.roles.indexOf(role);
                    role.addPlayerToBeRes(p);
                    this.roles.set(index, role);
                }
            }
        }
    }

    private void sendToInfectPlayerRes(Player p){
        for(LGplayer lGplayer : this.getRole(RoleSort.INFECT).getPlayers()) {
            if (lGplayer.getP() != p && !this.death.keySet().contains(lGplayer.getP())) {
                lGplayer.getP().sendMessage(ChatColor.LIGHT_PURPLE + p.getName() + " est mort de la main d'un loup. Vous avez 30 secondes pour l'infecter grâce à la commande" + ChatColor.BOLD + " /lg infecter " + p.getName());
                Rinfecteperedesloups role = (Rinfecteperedesloups) getRole(RoleSort.INFECT);
                int index = this.roles.indexOf(role);
                role.addPlayerToBeRes(p);
                this.roles.set(index, role);
            }
        }
    }

    public void infect(Player p){
        for(Role role : this.roles){
            if(main.lgsSorts.contains(role.getSort())){
                for(LGplayer lGplayer : role.getPlayers()){
                    lGplayer.getP().sendMessage(ChatColor.RED + "Un nouveau joueur a rejoint votre camps. /lg role pour voir la liste des loups");
                }
            }
            if(role.getSort() == RoleSort.ENFANT){
                for(LGplayer lGplayer : role.getPlayers()) {
                    if (((Renfantsauvage) role).isTransformed(lGplayer.getP())){
                        lGplayer.getP().sendMessage(ChatColor.RED + "Un nouveau joueur a rejoint votre camps. /lg role pour voir la liste des loups");
                    }
                }
            }
        }
        for(Role role : this.roles){
            for(LGplayer lGplayer : role.getPlayers()){
                if(lGplayer.isInfect()) {
                    lGplayer.getP().sendMessage(ChatColor.RED + "Un nouveau joueur a rejoint votre camps. /lg role pour voir la liste des loups");
                }
            }
        }
        global:
        for(Role role : this.roles){
            for(LGplayer lGplayer : role.getPlayers()){
                if(lGplayer.getP() == p){
                    int index = this.roles.indexOf(role);
                    List<LGplayer> lGplayers = role.getPlayers();
                    lGplayers.remove(lGplayer);
                    lGplayer.setInfect(true);
                    lGplayers.add(lGplayer);
                    role.setPlayers(lGplayers);
                    this.roles.set(index, role);
                    break global;
                }
            }
        }
        p.sendMessage(ChatColor.LIGHT_PURPLE + "Vous avez été infecté. Vous gardez votre rôle mais devez gagné avec les loups !");
        res(p);
    }

    public void res(Player p) {

        Rsorciere role = (Rsorciere) getRole(RoleSort.SORCIERE);
        int index = this.roles.indexOf(role);
        role.removePlayerBeResed(p);
        this.roles.set(index, role);
        Rinfecteperedesloups role2 = (Rinfecteperedesloups) getRole(RoleSort.INFECT);
        int index2 = this.roles.indexOf(role2);
        role2.removePlayerBeResed(p);
        this.roles.set(index2, role2);

        p.sendMessage(ChatColor.LIGHT_PURPLE + "Vous avez été réssucité");
        if(this.death.containsKey(p)) {
            this.death.remove(p);
            this.deathTasks.get(p).cancel();
            this.deathTasks.remove(p);
        }
        int x = main.API.random(this.border - 50, this.border - 100);
        if(x > 400){
            x = x - 300;
        }
        if(main.API.random(0, 1) == 1) x = (-1)*x;
        int z = main.API.random(this.border - 50, this.border - 100);
        if(z > 400){
            z = z - 300;
        }
        if(main.API.random(0, 1) == 1) z = (-1)*z;
        Location loc = new Location(Bukkit.getWorld("world"), x, 150, z);
        while (loc.getBlock().getType() == Material.AIR){
            loc.setY(loc.getY() - 1);
        }
        loc.setY(loc.getY() + 1);
        p.teleport(loc);
    }

    public void kill(Player p){
        Rsorciere role3 = (Rsorciere) getRole(RoleSort.SORCIERE);
        int index3 = this.roles.indexOf(role3);
        role3.removePlayerBeResed(p);
        this.roles.set(index3, role3);
        Rinfecteperedesloups role2 = (Rinfecteperedesloups) getRole(RoleSort.INFECT);
        int index2 = this.roles.indexOf(role2);
        role2.removePlayerBeResed(p);
        this.roles.set(index2, role2);

        p.setGameMode(GameMode.SPECTATOR);
        p.teleport(this.death.get(p));
        this.death.remove(p);
        this.deathTasks.remove(p);
        for(ItemStack it : p.getInventory()){
            if(it != null){
                p.getLocation().getWorld().dropItemNaturally(p.getLocation(), it);
            }
        }
        p.getInventory().clear();
        Role pRole = null;
        String msg = "";
        global:
        for(Role role : this.roles){
            for(LGplayer lGplayer : role.getPlayers()){
                if(lGplayer.getP() == p){
                    pRole = role;
                    if(lGplayer.isInfect()) {
                        msg = ChatColor.LIGHT_PURPLE + p.getName() + " (infecté) : " + pRole.getName();
                    }else{
                        msg = ChatColor.LIGHT_PURPLE + p.getName() + " : " + pRole.getName();
                    }
                    int index = this.roles.indexOf(role);
                    List<LGplayer> lGplayers = role.getPlayers();
                    lGplayers.remove(lGplayer);
                    role.setPlayers(lGplayers);
                    this.roles.set(index, role);
                    break global;
                }
            }
        }

        for(Player online : Bukkit.getOnlinePlayers()){
            online.playSound(online.getLocation(), Sound.ENTITY_WITHER_SPAWN, 10, 1);
        }

        Bukkit.broadcastMessage(ChatColor.RED + "====================");
        Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "Le village a perdu un de ses membres: " + p.getName() + ". Il était " + pRole.getName());
        Bukkit.broadcastMessage(ChatColor.RED + "====================");


        checkEnfant(p);

        if(this.couple != null) {
            if (isInCouple(p)) {
                msg = msg + " ❤";
                this.endMSG.add(msg);
                killByCouple(getCouple(p));
            } else {
                this.endMSG.add(msg);
                checkWin();
            }
        }else{
            this.endMSG.add(msg);
            checkWin();
        }
    }

    private void checkEnfant(Player p) {
        for(LGplayer lGplayer : this.getRole(RoleSort.ENFANT).getPlayers()){
            if(((Renfantsauvage)this.getRole(RoleSort.ENFANT)).hasModel(lGplayer.getP())){
                if(((Renfantsauvage)this.getRole(RoleSort.ENFANT)).getModel(lGplayer.getP()).equalsIgnoreCase(p.getName())){


                    for(Role role : this.roles){
                        if(main.lgsSorts.contains(role.getSort())){
                            for(LGplayer lGplayer2 : role.getPlayers()){
                                lGplayer2.getP().sendMessage(ChatColor.RED + "Un nouveau joueur a rejoint votre camps. /lg role pour voir la liste des loups");
                            }
                        }
                        if(role.getSort() == RoleSort.ENFANT){
                            for(LGplayer lGplayer2 : role.getPlayers()) {
                                if (((Renfantsauvage) role).isTransformed(lGplayer2.getP())){
                                    lGplayer2.getP().sendMessage(ChatColor.RED + "Un nouveau joueur a rejoint votre camps. /lg role pour voir la liste des loups");
                                }
                            }
                        }
                    }
                    for(Role role : this.roles){
                        for(LGplayer lGplayer2 : role.getPlayers()){
                            if(lGplayer2.isInfect()) {
                                lGplayer2.getP().sendMessage(ChatColor.RED + "Un nouveau joueur a rejoint votre camps. /lg role pour voir la liste des loups");
                            }
                        }
                    }

                    Renfantsauvage role = (Renfantsauvage) this.getRole(RoleSort.ENFANT);
                    int index = this.roles.indexOf(role);
                    role.transform(lGplayer.getP());
                    this.roles.set(index, role);
                    lGplayer.getP().sendMessage(ChatColor.LIGHT_PURPLE + "Votre modèle vient de mourir, vous appartenez maintenant au camps des loups. /lg role pour voir la liste des loups");
                }
            }
        }
    }

    private void killByCouple(Player p) {

        if(this.death.keySet().contains(p)){
            Rsorciere role3 = (Rsorciere) getRole(RoleSort.SORCIERE);
            int index3 = this.roles.indexOf(role3);
            role3.removePlayerBeResed(p);
            this.roles.set(index3, role3);
            Rinfecteperedesloups role2 = (Rinfecteperedesloups) getRole(RoleSort.INFECT);
            int index2 = this.roles.indexOf(role2);
            role2.removePlayerBeResed(p);
            this.roles.set(index2, role2);

            this.death.remove(p);
            this.deathTasks.remove(p);
        }

        p.setGameMode(GameMode.SPECTATOR);
        for(ItemStack it : p.getInventory()){
            if(it != null){
                p.getLocation().getWorld().dropItemNaturally(p.getLocation(), it);
            }
        }
        p.getInventory().clear();
        Role pRole = null;
        String msg = "";
        global:
        for(Role role : this.roles){
            for(LGplayer lGplayer : role.getPlayers()){
                if(lGplayer.getP() == p){
                    pRole = role;
                    if(lGplayer.isInfect()) {
                        msg = ChatColor.LIGHT_PURPLE + p.getName() + " (infecté) : " + pRole.getName() + " ❤";
                    }else{
                        msg = ChatColor.LIGHT_PURPLE + p.getName() + " : " + pRole.getName() + " ❤";
                    }
                    int index = this.roles.indexOf(role);
                    List<LGplayer> lGplayers = role.getPlayers();
                    lGplayers.remove(lGplayer);
                    role.setPlayers(lGplayers);
                    this.roles.set(index, role);
                    break global;
                }
            }
        }

        for(Player online : Bukkit.getOnlinePlayers()){
            online.playSound(online.getLocation(), Sound.ENTITY_WITHER_SPAWN, 10, 1);
        }

        this.endMSG.add(msg);

        Bukkit.broadcastMessage(ChatColor.RED + "====================");
        Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "Dans un élan d'amour, le village a perdu un autre des ses membres: " + p.getName() + ". Il était " + pRole.getName());
        Bukkit.broadcastMessage(ChatColor.RED + "====================");

        checkEnfant(p);

        checkWin();
    }

    private void checkWin() {

        if(!checkCoupleWin()) {
            if(!checkLGWin()) {
                if(!checkVillageoisWin()) {
                    checkSoloWin();
                }
            }
        }

    }

    private boolean checkCoupleWin() {

        if(this.couple != null) {
            boolean alive = false;

            for (Role role : this.roles) {
                for (LGplayer lGplayer : role.getPlayers()) {
                    if (couple.getP1() == lGplayer.getP()) {
                        alive = true;
                    }
                }
            }

            if (couple.getWin() == CoupleWin.SOLO) {
                boolean otherAlive = false;
                List<LGplayer> cupidons = getRole(RoleSort.CUPIDON).getPlayers();
                for (Role role : this.roles) {
                    for (LGplayer lGplayer : role.getPlayers()) {
                        if (lGplayer.getP() != couple.getP1()) {
                            if (lGplayer.getP() != couple.getP2()) {
                                if (!cupidons.contains(lGplayer)) {
                                    otherAlive = true;
                                }
                            }
                        }
                    }
                }
                if (!otherAlive) {
                    this.win(WinSort.COUPLE);
                    return true;
                }
            }
        }

        return false;

    }


    private boolean checkLGWin() {
        boolean hasOther = false;
        for(Role role : this.roles){
            if(role.getPlayers().size() > 0){
                if(role.getWin() != RoleWin.LOUPGAROU){
                    for(LGplayer lGplayer : role.getPlayers()){
                        if(!lGplayer.isInfect()){
                            hasOther = true;
                        }
                    }
                    if(role.getSort() == RoleSort.ENFANT){
                        for(LGplayer lGplayer : role.getPlayers()) {
                            if (!((Renfantsauvage) role).isTransformed(lGplayer.getP())) {
                                hasOther = true;
                            }
                        }
                    }
                }
            }
        }
        if(!hasOther){
            this.win(WinSort.LOUPS);
            return true;
        }
        return false;
    }


    private boolean checkVillageoisWin() {
        boolean hasOther = false;
        for(Role role : this.roles){
            if(role.getPlayers().size() > 0){
                if(role.getWin() != RoleWin.VILLAGE){
                    if(role.getSort() == RoleSort.ENFANT){
                        for(LGplayer lGplayer : role.getPlayers()) {
                            if (((Renfantsauvage) role).isTransformed(lGplayer.getP())) {
                                hasOther = true;
                            }
                        }
                    }else {
                        hasOther = true;
                    }
                }
            }
        }
        if(!hasOther){
            this.win(WinSort.VILLAGE);
            return true;
        }
        return false;
    }


    private boolean checkSoloWin() {

        for(Role role : this.roles){
            if(role.getPlayers().size() > 0){
                if(role.getWin() == RoleWin.SOLO){
                    for(Role roleTest : this.roles){
                        if(roleTest.getSort() != role.getSort()){
                            if(roleTest.getPlayers().size() > 0){
                                return false;
                            }
                        }
                    }
                    if(role.getPlayers().size() > 1){
                        return false;
                    }
                    if(role.getSort() == RoleSort.ASSASSIN){
                        this.win(WinSort.ASSASSIN);
                    }
                    if(role.getSort() == RoleSort.LOUPBLANC){
                        this.win(WinSort.LOUPBLANC);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private void win(WinSort sort) {


        for(Role role : this.roles){
            for(LGplayer lGplayer : role.getPlayers()){
                    Role pRole = role;
                    String msg = "";
                    if(lGplayer.isInfect()) {
                        msg = ChatColor.LIGHT_PURPLE + lGplayer.getP().getName() + " (infecté) : " + pRole.getName();
                    }else{
                        msg = ChatColor.LIGHT_PURPLE + lGplayer.getP().getName() + " : " + pRole.getName();
                    }
                    if(isInCouple(lGplayer.getP())){
                        msg = msg + " ❤";
                    }
                    this.endMSG.add(msg);
                }

        }

        Bukkit.broadcastMessage(ChatColor.GOLD + "La partie est finie:");
        Bukkit.broadcastMessage(ChatColor.GOLD + sort.getString());

        for(String msg : this.endMSG){
            Bukkit.broadcastMessage(msg);
        }

    }

    private boolean isAnAncienCanRes(Player p){
        for(LGplayer lGplayer : this.getRole(RoleSort.ANCIEN).getPlayers()){
            if(lGplayer.getP() == p){
                return ((Rancien)this.getRole(RoleSort.ANCIEN)).canRes(p);
            }
        }
        return false;
    }

    public List<Player> lgList(){
        List<Player> lgs = new ArrayList<>();
        for(Role role : this.roles){
            if(main.lgsSorts.contains(role.getSort())){
                for(LGplayer lGplayer : role.getPlayers()){
                    lgs.add(lGplayer.getP());
                }
            }
            if(role.getSort() == RoleSort.ENFANT){
                for(LGplayer lGplayer : role.getPlayers()){
                    if(((Renfantsauvage) role).isTransformed(lGplayer.getP())){
                        lgs.add(lGplayer.getP());
                    }
                }
            }
            for(LGplayer lGplayer : role.getPlayers()){
                if(lGplayer.isInfect()){
                    lgs.add(lGplayer.getP());
                }
            }
        }
        return lgs;
    }


    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        roles.addAll(this.roles);
        return roles;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    public Role getRole(Role role){
        for(Role thisRoles : roles){
            if(thisRoles.getName().equalsIgnoreCase(role.getName())){
                return thisRoles;
            }
        }
        return null;
    }

    public Couple getCouple() {
        return couple;
    }

    public void foundCouple(){
        this.couple.setFounded(true);
    }

    public boolean isInCouple(Player p){
        if(this.couple != null) {
            if (this.couple.getP1() == p || this.couple.getP2() == p) {
                return true;
            }
        }
        return false;

    }

    public Player getCouple(Player p){
        if(this.couple.getP1() == p){
            return this.couple.getP2();
        }
        if(this.couple.getP2() == p){
            return this.couple.getP1();
        }
        return null;
    }

    public void setRole(Role role, int players) {
        role.setNeededPlayers(players);
        this.roles.remove(getRole(role));
        this.roles.add(role);
        main.getConfig().set("role."+role.getName(), role.getNeededPlayers());
        main.saveConfig();
    }

    public void setRole(String name, int players) {
        Role role = this.getRole(name);
        int index = this.roles.indexOf(role);
        role.setNeededPlayers(players);
        this.roles.set(index, role);
        main.getConfig().set("role."+role.getName(), role.getNeededPlayers());
        main.saveConfig();
    }

    private Role getRole(String name) {
        for(Role role : this.roles){
            if(role.getSimpleName().equalsIgnoreCase(name)){
                return role;
            }
        }
        return null;
    }

    public boolean isRole(String name){
        for(Role role : this.roles){
            if(role.getSimpleName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public Role getRole(Player p){
        for(Role role : this.roles){
            for(LGplayer lGplayer : role.getPlayers()){
                if(lGplayer.getP() == p){
                    return role;
                }
            }
        }
        return null;
    }

    public Role getRole(RoleSort sort){
        for(Role role : this.roles){
            if(role.getSort() == sort){
                return role;
            }
        }
        return null;
    }

    public void setCouple(Player p1, Player p2) {
        this.couple = new Couple(p1, p2, main);
        Rcupidon role = (Rcupidon) this.getRole(RoleSort.CUPIDON);
        int index = this.roles.indexOf(role);
        role.setHasMadeACouple(true);
        this.roles.set(index, role);
    }

    public void voyanteFail(Player p) {

        Rvoyante role = (Rvoyante) this.getRole(RoleSort.VOYANTE);
        int index = this.roles.indexOf(role);
        role.lastSeeNotGood(p);
        this.roles.set(index, role);

    }

    public void voyanteSaw(Player p) {
        Rvoyante role = (Rvoyante) this.getRole(RoleSort.VOYANTE);
        int index = this.roles.indexOf(role);
        role.saw(p);
        this.roles.set(index, role);
    }

    public void renardSaw(Player p) {
        Rrenard role = (Rrenard) this.getRole(RoleSort.RENARD);
        int index = this.roles.indexOf(role);
        role.saw(p);
        this.roles.set(index, role);
    }

    public void salvateurProtect(Player p, Player protect) {
        Rsalvateur role = (Rsalvateur) this.getRole(RoleSort.SALVATEUR);
        int index = this.roles.indexOf(role);
        role.protect(p, protect);
        this.roles.set(index, role);
    }

    public boolean timeToVote = false;
    public Map<Player, Player> vote = new HashMap<>();

    @EventHandler
    public void episode(LGepisodeEvent e){
        if(e.getTimer() >= (Time.EPISODETIME.time() * Time.EPISODESTARTVOTE.time())) {
            int players = 0;
            for (Role role : this.roles) {
                for (LGplayer lGplayer : role.getPlayers()) {
                    players += 1;
                }
            }
            if (players >= 5) {
                timeToVote = true;
                vote = new HashMap<>();
            }
        }
    }

    @EventHandler
    public void vote(LGvoteendEvent e){
        if(timeToVote) {
            timeToVote = false;
            Map<Player, Integer> vote = new HashMap<>();
            for (Player voter : this.vote.keySet()) {
                if (vote.keySet().contains(this.vote.get(voter))) {
                    vote.put(this.vote.get(voter), vote.get(this.vote.get(voter)) + 1);
                } else {
                    vote.put(this.vote.get(voter), 1);
                }
            }

            Player voted = null;
            int preVote = 0;

            for (Player p : vote.keySet()) {
                if (voted == null) {
                    if (preVote < vote.get(p)) {
                        voted = p;
                        preVote = vote.get(p);
                    }
                } else {
                    if (preVote < vote.get(p)) {
                        voted = p;
                        preVote = vote.get(p);
                    } else {
                        if (preVote == vote.get(p)) {
                            voted = null;
                        }
                    }
                }
            }

            Bukkit.broadcastMessage(ChatColor.GOLD + "Résultats du vote:");

            if (voted == null) {
                Bukkit.broadcastMessage(ChatColor.BLUE + "Les villageois n'ont pas réussi à se mettre d'accord, personne ne perd de vie");
            }else{
                Bukkit.broadcastMessage(ChatColor.BLUE + "" + ChatColor.BOLD + voted.getName() + ChatColor.RESET + "" + ChatColor.BLUE + " est le joueur à avoir reçu le plus de vote (" + ChatColor.AQUA + preVote + ChatColor.BLUE + "). Ile perd 5 coeurs pour les 10 prochaines minutes");
                voted.setMaxHealth(voted.getMaxHealth() - 10);
                VoteTask voteTask = new VoteTask(voted);
                voteTask.runTaskTimer(main, 20, 20);
            }

        }
    }

    public void enqueterHasMade(Player enqueter, Player p1, Player p2) {
        Rdetective role = (Rdetective) this.getRole(RoleSort.DETECTIVE);
        int index = this.roles.indexOf(role);
        role.hasEnqueted(enqueter, p1, p2);
        this.roles.set(index, role);
    }

    private List<String> disconnected = new ArrayList<>();

    public boolean isInGame(Player p) {
        for(Role role : this.roles){
            for(LGplayer lGplayer : role.getPlayers()){
                if(lGplayer.getP().getName().equals(p.getName())){
                    return true;
                }
            }
        }
        return false;
    }

    public void disconnect(Player p) {

        disconnected.add(p.getName());

    }

    public boolean isDeconnected(Player p) {
        return this.disconnected.contains(p.getName());
    }

    public void reloadPlayer(Player p) {

        if(this.disconnected.contains(p.getName())){
            this.disconnected.remove(p.getName());
        }

        main.lgPermissionManager.addPerm(p, "role");
        main.lgPermissionManager.addPerm(p, "vote");
        main.lgPermissionManager.addPerm(p, "compo");

        main.scoreboardManager.reloadPlayer(p);

        for(Role role : this.roles){
            for(LGplayer lGplayer : role.getPlayers()){
                if(lGplayer.getP().getName().equals(p.getName())){
                    int index = this.roles.indexOf(role);
                    role.reloadPlayer(p);
                    this.roles.set(index, role);
                }
            }
        }

        if(this.couple != null) {
            this.couple.reloadPlayer(p);
        }

    }
}
