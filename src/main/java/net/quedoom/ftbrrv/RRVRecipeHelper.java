package net.quedoom.ftbrrv;

import cc.cassian.rrv.api.ActionType;
import cc.cassian.rrv.api.recipe.ItemView;
import cc.cassian.rrv.client.ReliableRecipeViewerClient;
import cc.cassian.rrv.common.overlay.itemlist.bookmark.BookmarkManager;
import cc.cassian.rrv.common.overlay.itemlist.panel.SidePanelOverlay;
import cc.cassian.rrv.common.overlay.itemlist.view.ItemViewOverlay;
import dev.ftb.mods.ftblibrary.client.config.Tristate;
import dev.ftb.mods.ftblibrary.client.gui.input.Key;
import dev.ftb.mods.ftbquests.integration.RecipeModHelper;
import dev.ftb.mods.ftbquests.quest.QuestObjectBase;
import net.minecraft.world.item.ItemStack;

import java.util.Collection;

public class RRVRecipeHelper implements RecipeModHelper {
    @Override
    public void refreshAll(RecipeModHelper.Components component) {
        switch (component) {
            case QUESTS -> refreshQuests();
            case LOOT_CRATES -> refreshLootcrates();
        }
    }

    @Override
    public void refreshRecipes(QuestObjectBase object) {
        object.componentsToRefresh().forEach(this::refreshAll);
    }

    @Override
    public boolean isRecipeModAvailable() {
        return true;
    }

    protected void refreshQuests() {
    }

    protected void refreshLootcrates() {
    }

    @Override
    public String getHelperName() {
        return "Reliable Recipe Viewer";
    }

    @Override
    public Tristate toggleBookmark(ItemStack stack) {
        if (SidePanelOverlay.showCraftables()) {
            BookmarkManager.INSTANCE.bookmarkItem(stack);
        } else {
            BookmarkManager.INSTANCE.removeItem(stack);
        }
        return Tristate.TRUE;
    }


    @Override
    public boolean isBookmarkKey(Key key) {
        return key.matches(ReliableRecipeViewerClient.ADD_BOOKMARK_KEYBIND);
    }

    @Override
    public void updateItemsDynamic(Collection<ItemStack> toRemove, Collection<ItemStack> toAdd) {
        for (ItemStack stack : toAdd) ItemView.addStackSensitive(stack);
        for (ItemStack stack : toRemove) ItemView.excludeItemStack(stack);
        FTBRRV.LOGGER.info("Removed {} items from RRV, added {} items", toRemove.size(), toAdd.size());
    }

    @Override
    public void showRecipes(ItemStack stack) {
        ItemViewOverlay.INSTANCE.openRecipeView(stack, ActionType.RESULT);
    }
//
//    @Override
//    public void showRecipes(FluidStack fluid) {
//        Fluid fluidVariant = fluid.fluid();
//        ItemViewOverlay.INSTANCE.openRecipeView(BuiltInRegistries.FLUID.getKey(fluidVariant), false);
//    }
}
