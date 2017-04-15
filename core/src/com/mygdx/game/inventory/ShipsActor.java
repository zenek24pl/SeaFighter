package com.mygdx.game.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

/**
 * Created by zenek on 15.04.2017.
 */

public class ShipsActor extends Window {
    public ShipsActor(Ships ships, DragAndDrop dragAndDrop, Skin skin) {
        super("Plansza od statkow wrog...", skin);

        TextButton closeButton = new TextButton("X", skin);
        closeButton.addListener(new HidingClickListener(this));
        //	getButtonTable().add(closeButton).height(getPadTop());

        setPosition(900, 100);
        setScale(3.2f);
        defaults().space(4);
        row().fill().expandX();

        int i = 0;
        for (Slot slot : ships.getSlots()) {
            SlotActor slotActor = new SlotActor(skin, slot);
            dragAndDrop.addSource(new SlotSource(slotActor));
            dragAndDrop.addTarget(new SlotTarget(slotActor));
            add(slotActor);

            i++;
            if (i % 2 == 0) {
                row();
            }
        }

        pack();

        // it is hidden by default
        setVisible(false);
    }
}
