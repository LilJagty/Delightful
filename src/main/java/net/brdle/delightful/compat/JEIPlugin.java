package net.brdle.delightful.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.brdle.delightful.Util;
import net.brdle.delightful.common.config.DelightfulConfig;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.knife.DelightfulKnifeItem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.utility.TextUtils;
import javax.annotation.ParametersAreNonnullByDefault;

@JeiPlugin
@ParametersAreNonnullByDefault
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin
{
    private static final ResourceLocation ID = Util.rl(FarmersDelight.MODID, "jei_plugin");
    private static final Minecraft MC = Minecraft.getInstance();

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        // Remove all disabled Items from JEI
        registration.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK,
            DelightfulItems.ITEMS.getEntries().stream()
                .filter(k -> !(DelightfulConfig.verify(k))) // Keep only Items that are not enabled in the config
                .map(Util::gs) // Get ItemStack
                .toList());
        // Add Knife translations
        DelightfulItems.ITEMS.getEntries().stream()
            .map(RegistryObject::get)
            .filter(k -> k instanceof DelightfulKnifeItem && ((DelightfulKnifeItem) k).isEnabled())
            .map(ItemStack::new)
            .forEach((k -> registration.addIngredientInfo(k, VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.knife"))));
        if (DelightfulConfig.verify("green_tea_leaf") && !ModList.get().isLoaded("farmersrespite")) {
            registration.addIngredientInfo(DelightfulItems.GREEN_TEA_LEAF.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("delightful.green_tea_leaf.desc"));
        }
        if (DelightfulConfig.verify("acorn")) {
            registration.addIngredientInfo(Util.gs(DelightfulItems.ACORN), VanillaTypes.ITEM_STACK, Component.translatable("delightful.acorn.desc"));
        }
        if (DelightfulConfig.verify("salmonberries")) {
            registration.addIngredientInfo(Util.gs(DelightfulItems.SALMONBERRIES), VanillaTypes.ITEM_STACK, Component.translatable("delightful.salmonberries.desc"));
        }
        if (DelightfulConfig.verify("mini_melon")) {
            registration.addIngredientInfo(Util.gs(DelightfulItems.MINI_MELON), VanillaTypes.ITEM_STACK, Component.translatable("delightful.mini_melon.desc"));
        }
        if (DelightfulConfig.verify("cantaloupe")) {
            registration.addIngredientInfo(Util.gs(DelightfulItems.CANTALOUPE), VanillaTypes.ITEM_STACK, Component.translatable("delightful.cantaloupe.desc").append(" ").append(Component.translatable("delightful.sliceable.desc")));
        }
        if (DelightfulConfig.verify("animal_fat")) {
            registration.addIngredientInfo(Util.gs(DelightfulItems.ANIMAL_FAT), VanillaTypes.ITEM_STACK, Component.translatable("delightful.animal_fat.desc"));
        }
        if (DelightfulConfig.verify("animal_oil_bottle")) {
            registration.addIngredientInfo(Util.gs(DelightfulItems.ANIMAL_OIL_BOTTLE), VanillaTypes.ITEM_STACK, Component.translatable("delightful.animal_oil_bottle.desc"));
        }
        registration.addIngredientInfo(Items.MELON.getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("delightful.sliceable.desc"));
        registration.addIngredientInfo(Items.PUMPKIN.getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("delightful.sliceable.desc"));
    }
    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }
}