package com.ymnaytka.ivmmcore.common.data;

import com.tterrag.registrate.providers.ProviderType;
import com.ymnaytka.ivmmcore.api.registries.IVMMCoreRegistration;
import com.ymnaytka.ivmmcore.common.data.lang.IVMMCoreLangHandler;
import com.ymnaytka.ivmmcore.common.data.tag.IVMMCoreTagsHandler;

public class IVMMCoreDatagen {
    public static void init() {
        IVMMCoreRegistration.REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, IVMMCoreTagsHandler::initItem);
        IVMMCoreRegistration.REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, IVMMCoreTagsHandler::initBlock);
        IVMMCoreRegistration.REGISTRATE.addDataGenerator(ProviderType.FLUID_TAGS, IVMMCoreTagsHandler::initFluid);
        IVMMCoreRegistration.REGISTRATE.addDataGenerator(ProviderType.ENTITY_TAGS, IVMMCoreTagsHandler::initEntity);
        IVMMCoreRegistration.REGISTRATE.addDataGenerator(ProviderType.LANG, IVMMCoreLangHandler::init);
    }
}
