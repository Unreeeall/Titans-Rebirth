package me.unreal.titansrebirth.util;

import me.unreal.titansrebirth.TitansRebirth;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class TRTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_DAWN_TOOL = createTag("needs_dawn_tool");
        public static final TagKey<Block> INCORRECT_FOR_DAWN_TOOL = createTag("needs_dawn_tool");


        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(TitansRebirth.MOD_ID, name));
        }
    }
}
