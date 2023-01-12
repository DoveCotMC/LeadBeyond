package io.github.dovecotmc.leadbeyond.common.data;

import com.simibubi.create.content.logistics.trains.management.edgePoint.station.StationTileEntity;
import io.github.dovecotmc.leadbeyond.LeadBeyond;
import io.github.dovecotmc.leadbeyond.common.util.phys.Pos2i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

import java.util.ArrayList;
import java.util.List;

public class Station {
    private final String name;
    private Pos2i from;
    private Pos2i to;
    private int color;
    private final List<StationTileEntity> stationAnchors;

    public Station(String name, int color, Pos2i from, Pos2i to) {
        this.name = name;
        this.color = color;
        this.from = from;
        this.to = to;
        this.stationAnchors = new ArrayList<>();
    }

    public Station(String name, int color, int fromX, int fromY, int toX, int toY) {
        this(name, color, new Pos2i(fromX, fromY), new Pos2i(toX, toY));
    }

    public List<StationTileEntity> getStationAnchors() {
        return stationAnchors;
    }

    public Pos2i getFrom() {
        return from;
    }

    public void setFrom(Pos2i from) {
        this.from = from;
    }

    public void setFrom(int x, int y) {
        this.from = new Pos2i(x, y);
    }

    public Pos2i getTo() {
        return to;
    }

    public void setTo(Pos2i to) {
        this.to = to;
    }

    public void setTo(int x, int y) {
        this.to = new Pos2i(x, y);
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public static void writeToTag(CompoundTag tag, Station station) {
        CompoundTag sub = new CompoundTag();
        sub.putString("name", station.getName());
        Pos2i.writeToTag(sub, station.getFrom(), "from");
        Pos2i.writeToTag(sub, station.getTo(), "to");
        ListTag anchorList = new ListTag();
        for (StationTileEntity tile : station.getStationAnchors()) {
            anchorList.addTag(anchorList.size(),
                    new Pos2i(tile.getBlockPos().getX(), tile.getBlockPos().getZ()).toTag());
        }
        sub.put("stationAnchors", anchorList);
        if (!tag.contains(station.name)) {
            tag.put(station.name, sub);
        } else {
            LeadBeyond.LOGGER.error("Failed to write station "
                    + station.name +
                    " to tag since the key is already occupied by others!");
        }
    }

    public static Station readFromTag(CompoundTag tag, String name) {
        CompoundTag sub = tag.getCompound(name);
        // TODO: parsing the NBT to correct form
        throw new AssertionError();
    }
}
