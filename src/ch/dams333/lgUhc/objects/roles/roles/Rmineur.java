package ch.dams333.lgUhc.objects.roles.roles;

import ch.dams333.lgUhc.LgUhc;
import ch.dams333.lgUhc.objects.roles.Role;
import ch.dams333.lgUhc.objects.roles.RoleSort;
import ch.dams333.lgUhc.objects.roles.RoleWin;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Rmineur extends Role {
    public Rmineur(LgUhc main) {
        super(main);
    }

    @Override
    public String getName() {
        return "Mineur";
    }

    @Override
    public String getSimpleName() {
        return "Mineur";
    }

    @Override
    public String getDescription() {
        return "Vous minez plus vite. Vous gagnez si vous éliminez tous les loups";
    }
    @Override
    public RoleSort getSort() {
        return RoleSort.MINEUR;
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

    }

    @Override
    public void playerGives(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 999999, 1, true), true);
        ItemStack it = main.API.itemStackManager.create(Material.DIAMOND_PICKAXE);
        it.addUnsafeEnchantment(Enchantment.DIG_SPEED, 3);
        p.getInventory().addItem(it);
    }
}
