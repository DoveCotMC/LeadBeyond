package io.github.dovecotmc.leadbeyond.common.data.save;

import io.github.dovecotmc.leadbeyond.common.data.Station;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StationSavedData extends SavedData {
    private final List<Station> stations;

    public StationSavedData() {
        this.stations = new ArrayList<>();
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag tag) {
        // TODO: complete this part
        throw new AssertionError();
    }
}
