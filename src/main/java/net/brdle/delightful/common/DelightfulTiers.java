package net.brdle.delightful.common;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum DelightfulTiers implements Tier {
    COPPER(2, 150, 5.0F, 1.5F, 14, () -> Ingredient.of(Items.COPPER_INGOT)),
    BONE(1, 190, 5.0F, 1.5F, 9, () -> Ingredient.of(Items.BONE)),
    TIN(1, 120, 13.0F, 1.5F, 18, DelightfulIngredients.getIngot("tin")),
    STEEL(2, 484, 6.5F, 2.5F, 16, DelightfulIngredients.getIngot("steel")),
    SILVER(2, 484, 6.0F, 2.0F, 16, DelightfulIngredients.getIngot("silver")),
    BRASS(2, 484, 6.0F, 2.5F, 16, DelightfulIngredients.getIngot("brass")),
    ENDERITE(5, 2401, 10.0F, 5.0F, 15, DelightfulIngredients.getIngot("enderite")),
    OBSIDIAN_INFUSED_ENDERITE(5, 2771, 11.0F, 6.0F, 15, DelightfulIngredients.getIngot("obsidian_infused_enderite")),
    BRONZE(2, 375, 3.0F, 2.0F, 10, DelightfulIngredients.getIngot("bronze")),
    OSMIUM(3, 1024, 4.0F, 4.0F, 14, DelightfulIngredients.getIngot("osmium")),
    REFINED_GLOWSTONE(2, 384, 15.0F, 2.0F, 20, DelightfulIngredients.getIngot("refined_glowstone")),
    REFINED_OBSIDIAN(6, 4096, 12.0F, 8.0F, 18, DelightfulIngredients.getIngot("refined_obsidian")),
    LAPIS_LAZULI(1, 128, 4.0F, 1.0F, 32, DelightfulIngredients.getGem("lapis")),
    LARGE_AMETHYST(4, 2625, 12.0F, 4.0F, 35, DelightfulIngredients.getGem("large_amethyst")),
    BLACK_OPAL(5, 5250, 16.0F, 5.0F, 15, DelightfulIngredients.getGem("black_opal")),
    NETHERITE_OPAL(6, 6300, 16.0F, 6.0F, 20, DelightfulIngredients.getGem("black_opal")),
    CONSTANTAN(2, 250, 5.5F, 2.0F, 10, DelightfulIngredients.getIngot("constantan")),
    ELECTRUM(2, 96, 13.0F, 2.0F, 28, DelightfulIngredients.getIngot("electrum")),
    INVAR(2, 300, 7.0F, 2.5F, 13, DelightfulIngredients.getIngot("invar")),
    LEAD(1, 130, 12.0F, 1.5F, 16, DelightfulIngredients.getIngot("lead")),
    NICKEL(2, 225, 7.0F, 2.5F, 12, DelightfulIngredients.getIngot("nickel"));

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    DelightfulTiers(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = Suppliers.memoize(repairIngredient);
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}