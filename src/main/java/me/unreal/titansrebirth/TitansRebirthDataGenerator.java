package me.unreal.titansrebirth;

import me.unreal.titansrebirth.datagen.TRModelProvider;
import me.unreal.titansrebirth.datagen.TRRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TitansRebirthDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(TRModelProvider::new);
		pack.addProvider(TRRecipeProvider::new);
	}
}
