package ch.dams333.lgUhc.objects.roles.roles;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.player.LGplayer;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.RoleWin;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Rassassin extends Role {
    public Rassassin(LgUhc main) {
        super(main);
    }

    @Override
    public String getName() {
        return "Assassin";
    }

    @Override
    public String getSimpleName() {
        return "Assassin";
    }

    @Override
    public String getDescription() {
        return "Vous avez force le jour. Vous possédez des livres d'enchantement. Votre but est de gagner seul";
    }

    @Override
    public RoleSort getSort() {
        return RoleSort.ASSASSIN;
    }

    @Override
    public RoleWin getWin() {
        return RoleWin.SOLO;
    }

    @Override
    public void night() {
        for(LGplayer lGplayer : super.getPlayers()){
            Player p = lGplayer.getP();
            p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        }
    }

    @Override
    public void day() {
        for(LGplayer lGplayer : super.getPlayers()){
            Player p = lGplayer.getP();
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 0, false, false), true);
        }
    }

    @Override
    public void episode() {

    }

    @Override
    protected void reloadPerm(Player p) {

    }

    @Override
    public void playerGives(Player p) {
        ItemStack book = main.API.itemStackManager.create(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta m = (EnchantmentStorageMeta) book.getItemMeta();
        m.addStoredEnchant(Enchantment.ARROW_DAMAGE, 3, true);
        book.setItemMeta(m);
        p.getInventory().addItem(book);
        m.removeStoredEnchant(Enchantment.ARROW_DAMAGE);
        m.addStoredEnchant(Enchantment.DAMAGE_ALL, 3, true);
        book.setItemMeta(m);
        p.getInventory().addItem(book);
        m.removeStoredEnchant(Enchantment.DAMAGE_ALL);
        m.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        book.setItemMeta(m);
        p.getInventory().addItem(book);
    }
}
