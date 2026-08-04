package com.ymnaytka.ivmmcore.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.ToolProperty;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.utils.memoization.GTMemoizer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class IVMMCoreModification {

    public static void modifyMaterials() {
        Copper.setProperty(PropertyKey.TOOL,
                (ToolProperty.Builder
                        .of(5, 3.0f, 128, 2, GTToolType.PICKAXE, GTToolType.SHOVEL, GTToolType.HOE, GTToolType.AXE,
                                GTToolType.SWORD, GTToolType.FILE, GTToolType.HARD_HAMMER, GTToolType.KNIFE,
                                GTToolType.SAW, GTToolType.MORTAR, GTToolType.SCREWDRIVER, GTToolType.WRENCH)
                        .build()));
        rock.setIgnored(Marble, GTMemoizer.memoizeBlockSupplier(() -> GTBlocks.MARBLE.get()));


    }
}
