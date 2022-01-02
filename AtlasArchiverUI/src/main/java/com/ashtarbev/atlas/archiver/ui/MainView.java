package com.ashtarbev.atlas.archiver.ui;

import com.ashtarbev.atlas.archiver.core.item.Item;
import com.ashtarbev.atlas.archiver.ui.provider.ItemsDataCallback;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.router.Route;

import java.util.stream.Stream;

@Route
public class MainView extends VerticalLayout {

    public MainView(ItemsDataCallback itemsDataCallback) {
        Grid<Item> grid = new Grid<>();
        DataProvider.fromCallbacks(itemsDataCallback, itemsDataCallback);
        grid.setItems(itemsDataCallback);
        grid.addColumn(Item::getTitle).setHeader("Title");
        grid.addColumn(Item::getImageUrl).setHeader("Image URL");
        grid.addColumn(Item::getUrl).setHeader("Link URL");
        add(grid);
    }
}
