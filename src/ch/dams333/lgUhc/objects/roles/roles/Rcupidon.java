package ch.dams333.lgUhc.objects.roles.roles;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.RoleWin;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class Rcupidon extends Role {


    public boolean hasMadeACouple() {
        return hasMadeACouple;
    }

    public void setHasMadeACouple(boolean hasMadeACouple) {
        this.hasMadeACouple = hasMadeACouple;
    }

    public Rcupidon(LgUhc main) {
        super(main);
        hasMadeACouple = false;

    }

    private boolean hasMadeACouple;

    @Override
    public String getName() {
        return "Cupidon";
    }

    @Override
    public String getSimpleName() {
        return "Cupidon";
    }

    @Override
    public String getDescription() {
        return "Vous devez choisir un couple avec la commande /lg love. Vote but est de tuer tous les loups";
    }

    @Override
    public RoleSort getSort() {
        return RoleSort.CUPIDON;
    }

    @Override
    public RoleWin getWin() {
        return RoleWin.VILLAGE;
    }

    @Override
    public void night() {

    }

    @Override
    public void day() {

    }

    @Override
    public void episode() {

    }

    @Override
    protected void reloadPerm(Player p) {
        main.lgPermissionManager.addPerm(p, "love");
    }

    @Override
    public void playerGives(Player p) {
        p.getInventory().addItem(main.API.itemStackManager.create(Material.STRING, 3));
        p.getInventory().addItem(main.API.itemStackManager.create(Material.ARROW, 64));
        ItemStack book = main.API.itemStackManager.create(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta m = (EnchantmentStorageMeta) book.getItemMeta();
        m.addStoredEnchant(Enchantment.ARROW_KNOCKBACK, 2, true);
        book.setItemMeta(m);
        p.getInventory().addItem(book);
        main.lgPermissionManager.addPerm(p, "love");
    }
}
