package net.moonlightflower.wc3libs.misc.model;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObservableLinkedHashSet;
import net.moonlightflower.wc3libs.misc.ObservableLinkedHashSetView;
import net.moonlightflower.wc3libs.misc.model.mdx.*;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class MDX {
    private final ObservableLinkedHashSet<Chunk> _chunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<Chunk> getChunks() {
        return _chunks;
    }

    @Nonnull
    public Optional<VersionChunk> getVersionChunk() {
        return _chunks.stream().filter(chunk -> chunk instanceof VersionChunk).map(chunk -> (VersionChunk) chunk).findFirst();
    }

    @Nonnull
    public final Optional<ModelInfoChunk> getModelInfoChunk() {
        return _chunks.stream().filter(chunk -> chunk instanceof ModelInfoChunk).map(chunk -> (ModelInfoChunk) chunk).findFirst();
    }

    private final LinkedHashSet<SequenceChunk> _sequenceChunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof SequenceChunk);

    @Nonnull
    public LinkedHashSet<SequenceChunk> getSequenceChunks() {
        return _sequenceChunks;
    }

    private final LinkedHashSet<GlobalSequenceChunk> _globalSequenceChunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof
            GlobalSequenceChunk);

    @Nonnull
    public LinkedHashSet<GlobalSequenceChunk> getGlobalSequenceChunks() {
        return _globalSequenceChunks;
    }

    private final LinkedHashSet<TextureChunk> _textureChunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof TextureChunk);

    @Nonnull
    public LinkedHashSet<TextureChunk> getTextureChunks() {
        return _textureChunks;
    }

    private final LinkedHashSet<SoundChunk> _soundChunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof SoundChunk);

    @Nonnull
    public LinkedHashSet<SoundChunk> getSoundChunks() {
        return _soundChunks;
    }

    private final LinkedHashSet<MaterialChunk> _materialChunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof MaterialChunk);

    @Nonnull
    public LinkedHashSet<MaterialChunk> getMaterialChunks() {
        return _materialChunks;
    }

    private final LinkedHashSet<TexAnimChunk> _texAnimChunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof TexAnimChunk);

    @Nonnull
    public LinkedHashSet<TexAnimChunk> getTextureAnimChunks() {
        return _texAnimChunks;
    }

    private final LinkedHashSet<GeosetChunk> _geosetChunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof GeosetChunk);

    @Nonnull
    public LinkedHashSet<GeosetChunk> getGeosetChunks() {
        return _geosetChunks;
    }

    private final LinkedHashSet<GeosetAnimChunk> _geosetAnimChunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof GeosetAnimChunk);

    @Nonnull
    public LinkedHashSet<GeosetAnimChunk> getGeosetAnimChunks() {
        return _geosetAnimChunks;
    }

    private final LinkedHashSet<BoneChunk> _boneChunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof BoneChunk);

    @Nonnull
    public LinkedHashSet<BoneChunk> getBoneChunks() {
        return _boneChunks;
    }

    private final LinkedHashSet<LightChunk> _lightChunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof LightChunk);

    @Nonnull
    public LinkedHashSet<LightChunk> getLightChunks() {
        return _lightChunks;
    }

    private final LinkedHashSet<HelperChunk> _helperChunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof HelperChunk);

    @Nonnull
    public LinkedHashSet<HelperChunk> getHelperChunks() {
        return _helperChunks;
    }

    private final LinkedHashSet<AttachmentChunk> _attachmentChunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof AttachmentChunk);

    @Nonnull
    public LinkedHashSet<AttachmentChunk> getAttachmentChunks() {
        return _attachmentChunks;
    }

    private final LinkedHashSet<PivotPointChunk> _pivotPointChunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof PivotPointChunk);

    @Nonnull
    public LinkedHashSet<PivotPointChunk> getPivotPointChunks() {
        return _pivotPointChunks;
    }

    private final LinkedHashSet<ParticleEmitterChunk> _particleEmitterChunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof
            ParticleEmitterChunk);

    @Nonnull
    public LinkedHashSet<ParticleEmitterChunk> getParticleEmitterChunks() {
        return _particleEmitterChunks;
    }

    private final LinkedHashSet<ParticleEmitter2Chunk> _particleEmitter2Chunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof
            ParticleEmitter2Chunk);

    @Nonnull
    public LinkedHashSet<ParticleEmitter2Chunk> getParticleEmitter2Chunks() {
        return _particleEmitter2Chunks;
    }

    private final LinkedHashSet<RibbonEmitterChunk> _ribbonEmitterChunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof
            RibbonEmitterChunk);

    @Nonnull
    public LinkedHashSet<RibbonEmitterChunk> getRibbonEmitterChunks() {
        return _ribbonEmitterChunks;
    }

    private final LinkedHashSet<EventObjectChunk> _eventObjectChunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof EventObjectChunk);

    @Nonnull
    public LinkedHashSet<EventObjectChunk> getEventObjectChunks() {
        return _eventObjectChunks;
    }

    private final LinkedHashSet<CameraChunk> _cameraChunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof CameraChunk);

    @Nonnull
    public LinkedHashSet<CameraChunk> getCameraChunks() {
        return _cameraChunks;
    }

    private final LinkedHashSet<CollisionShapeChunk> _collisionShapeChunks = new ObservableLinkedHashSetView<>(_chunks, chunk -> chunk instanceof
            CollisionShapeChunk);

    @Nonnull
    public LinkedHashSet<CollisionShapeChunk> getCollisionShapeChunks() {
        return _collisionShapeChunks;
    }

    public static class EncodingFormat extends Format<EncodingFormat.Enum> {
        public enum Enum {
            AUTO,
            MDX_0x0
        }

        private static Map<Integer, MDX.EncodingFormat> _map = new LinkedHashMap<>();

        public final static EncodingFormat AUTO = new MDX.EncodingFormat(Enum.AUTO, -1);
        public final static EncodingFormat MDX_0x0 = new MDX.EncodingFormat(Enum.MDX_0x0, 0x0);

        public static EncodingFormat valueOf(int version) {
            return _map.get(version);
        }

        private EncodingFormat(@Nonnull Enum enumVal, int version) {
            super(enumVal, version);

            _map.put(version, this);
        }
    }

    public static Id TOKEN = Id.valueOf("MDLX");

    public interface TokenHandler {
        void run() throws BinStream.StreamException;
    }

    private void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        Id startToken = stream.readId("startToken");

        if (!startToken.equals(TOKEN)) throw new IllegalArgumentException("invalid " + TOKEN + " startToken (" + startToken + ")");

        Map<Id, TokenHandler> _tokenMap = new LinkedHashMap<>();

        _tokenMap.put(VersionChunk.TOKEN, () -> _chunks.add(new VersionChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(ModelInfoChunk.TOKEN, () -> _chunks.add(new ModelInfoChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(SequenceChunk.TOKEN, () -> _sequenceChunks.add(new SequenceChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(GlobalSequenceChunk.TOKEN, () -> _globalSequenceChunks.add(new GlobalSequenceChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(TextureChunk.TOKEN, () -> _textureChunks.add(new TextureChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(SoundChunk.TOKEN, () -> _soundChunks.add(new SoundChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(MaterialChunk.TOKEN, () -> _materialChunks.add(new MaterialChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(TexAnimChunk.TOKEN, () -> _texAnimChunks.add(new TexAnimChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(GeosetChunk.TOKEN, () -> _geosetChunks.add(new GeosetChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(GeosetAnimChunk.TOKEN, () -> _geosetAnimChunks.add(new GeosetAnimChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(BoneChunk.TOKEN, () -> _boneChunks.add(new BoneChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(LightChunk.TOKEN, () -> _lightChunks.add(new LightChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(HelperChunk.TOKEN, () -> _helperChunks.add(new HelperChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(AttachmentChunk.TOKEN, () -> _attachmentChunks.add(new AttachmentChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(PivotPointChunk.TOKEN, () -> _pivotPointChunks.add(new PivotPointChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(ParticleEmitterChunk.TOKEN, () -> _particleEmitterChunks.add(new ParticleEmitterChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(ParticleEmitter2Chunk.TOKEN, () -> _particleEmitter2Chunks.add(new ParticleEmitter2Chunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(RibbonEmitterChunk.TOKEN, () -> _ribbonEmitterChunks.add(new RibbonEmitterChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(EventObjectChunk.TOKEN, () -> _eventObjectChunks.add(new EventObjectChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(CameraChunk.TOKEN, () -> _cameraChunks.add(new CameraChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(CollisionShapeChunk.TOKEN, () -> _collisionShapeChunks.add(new CollisionShapeChunk(stream, EncodingFormat.MDX_0x0)));

        while (!stream.eof()) {
            Id chunkToken = stream.readId("chunkToken");

            if (_tokenMap.containsKey(chunkToken)) {
                TokenHandler handler = _tokenMap.get(chunkToken);

                stream.rewind(4);

                handler.run();
            } else {
                System.err.println("unknown chunk " + chunkToken + ";" + Arrays.toString(chunkToken.toString().getBytes()));

                long size = stream.readUInt32("header_size");

                stream.skip(size);
            }
        }
    }

    private void write_0x0(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        stream.writeId(TOKEN);

        for (Chunk chunk : getChunks()) {
            chunk.write(stream, EncodingFormat.MDX_0x0);
        }
    }

    private void read_auto(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        read(stream, EncodingFormat.MDX_0x0);
    }

    private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
        switch (format.toEnum()) {
            case AUTO: {
                read_auto(stream);

                break;
            }
            case MDX_0x0: {
                read_0x0(stream);

                break;
            }
        }
    }

    private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
        switch (format.toEnum()) {
            case AUTO:
            case MDX_0x0: {
                write_0x0(stream);

                break;
            }
        }
    }

    private void read(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        read(stream, MDX.EncodingFormat.AUTO);
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    private void read(@Nonnull File file, @Nonnull MDX.EncodingFormat format) throws IOException {
        read(new Wc3BinInputStream(file), format);
    }

    public void write(@Nonnull File file, @Nonnull MDX.EncodingFormat format) throws IOException {
        Wc3BinOutputStream outStream = new Wc3BinOutputStream(file);

        write(outStream, format);

        outStream.close();
    }

    private void read(@Nonnull File file) throws IOException {
        read(file, MDX.EncodingFormat.AUTO);
    }

    public void write(@Nonnull File file) throws IOException {
        write(new Wc3BinOutputStream(file));
    }

    public MDX() {
    }

    public MDX(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        read(stream);
    }

    public MDX(@Nonnull File file) throws IOException {
        read(file);
    }

    public void squish() {
        for (Chunk chunk : getChunks()) {
            if (chunk instanceof GeosetChunk) {
                squishGeoset((GeosetChunk)chunk);
            } else if (chunk instanceof PivotPointChunk) {
                squishPivot((PivotPointChunk)chunk);
            } else if (chunk instanceof BoneChunk) {
                squishBone((BoneChunk)chunk);
            }
        }
    }

    private void squishBone(BoneChunk chunk) {
        chunk.getBones().forEach(bone -> {
            squishNode(bone.getNode());
        });
    }

    private void squishNode(Node node) {
        node.getRotationTrackChunks().forEach(rotationTrackChunk -> {
            rotationTrackChunk.getRotationTracks().forEach(rotationTrack -> {
                rotationTrack.setRotation(rotationTrack.getRotation().squish());
                rotationTrack.setInTanRotation(rotationTrack.getInTanRotation().squish());
                rotationTrack.setOutTanRotation(rotationTrack.getOutTanRotation().squish());
            });
        });
        node.getTranslationTrackChunks().forEach(translationTrackChunk -> {
            translationTrackChunk.getTranslationTracks().forEach(translationTrack -> {
                translationTrack.setTranslation(translationTrack.getTranslation().squish());
                translationTrack.setInTanTranslation(translationTrack.getInTanTranslation().squish());
                translationTrack.setOutTanTranslation(translationTrack.getOutTanTranslation().squish());
            });
        });
        node.getScalingTrackChunks().forEach(scalingTrackChunk -> {
            scalingTrackChunk.getScalingTracks().forEach(scalingTrack -> {
                scalingTrack.setScaling(scalingTrack.getScaling().squish());
                scalingTrack.setInTanScaling(scalingTrack.getInTanScaling().squish());
                scalingTrack.setOutTanScaling(scalingTrack.getOutTanScaling().squish());
            });
        });
    }

    private void squishPivot(PivotPointChunk chunk) {
        chunk.getPivotPoints().forEach(pivotPoint -> {
            pivotPoint.setPos(pivotPoint.getPos().squish());
        });
    }

    private void squishGeoset(GeosetChunk chunk) {
        chunk.getGeosets().forEach(geoset -> {
            geoset.getVertexChunk().getVertices().forEach(vertex -> {
                vertex.setPos(vertex.getPos().squish());
            });

            geoset.getVertexNormalChunk().getVertices().forEach(vertex -> {
                vertex.setPos(vertex.getPos().squish());
            });

            geoset.getTexCoordSetChunk().getTexCoordSets().forEach(texCoordSet -> {
                texCoordSet.getTexCoords().forEach(texCoord -> {
                    texCoord.setPos(texCoord.getPos().squish());
                });
            });
        });
    }
}
