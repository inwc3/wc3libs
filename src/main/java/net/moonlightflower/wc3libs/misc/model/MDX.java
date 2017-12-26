package net.moonlightflower.wc3libs.misc.model;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.mdx.*;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MDX {
    private List<VersionChunk> _versionChunks = new ArrayList<>();

    @Nonnull
    public List<VersionChunk> getVersionChunks() {
        return new ArrayList<>(_versionChunks);
    }

    private void addVersionChunk(@Nonnull VersionChunk val) {
        _versionChunks.add(val);
    }

    private List<ModelInfoChunk> _modelInfoChunks = new ArrayList<>();

    @Nonnull
    public List<ModelInfoChunk> getModelInfoChunks() {
        return new ArrayList<>(_modelInfoChunks);
    }

    private void addModelInfoChunk(@Nonnull ModelInfoChunk val) {
        _modelInfoChunks.add(val);
    }

    private List<SequenceChunk> _sequenceChunk = new ArrayList<>();

    @Nonnull
    public List<SequenceChunk> getSequenceChunks() {
        return new ArrayList<>(_sequenceChunk);
    }

    private void addSequenceChunk(@Nonnull SequenceChunk val) {
        _sequenceChunk.add(val);
    }

    private List<GlobalSequenceChunk> _globalSequenceChunk = new ArrayList<>();

    @Nonnull
    public List<GlobalSequenceChunk> getGlobalSequenceChunks() {
        return new ArrayList<>(_globalSequenceChunk);
    }

    private void addGlobalSequenceChunk(@Nonnull GlobalSequenceChunk val) {
        _globalSequenceChunk.add(val);
    }

    private List<TextureChunk> _textureChunk = new ArrayList<>();

    @Nonnull
    public List<TextureChunk> getTextureChunks() {
        return new ArrayList<>(_textureChunk);
    }

    private void addTextureChunk(@Nonnull TextureChunk val) {
        _textureChunk.add(val);
    }

    private List<SoundChunk> _soundChunk = new ArrayList<>();

    @Nonnull
    public List<SoundChunk> getSoundChunks() {
        return new ArrayList<>(_soundChunk);
    }

    private void addSoundChunk(@Nonnull SoundChunk val) {
        _soundChunk.add(val);
    }

    private List<MaterialChunk> _materialChunk = new ArrayList<>();

    @Nonnull
    public List<MaterialChunk> getMaterialChunks() {
        return new ArrayList<>(_materialChunk);
    }

    private void addMaterialChunk(@Nonnull MaterialChunk val) {
        _materialChunk.add(val);
    }

    private List<TexAnimChunk> _texAnimChunk = new ArrayList<>();

    @Nonnull
    public List<TexAnimChunk> getTextureAnimChunks() {
        return new ArrayList<>(_texAnimChunk);
    }

    private void addTextureAnimChunk(@Nonnull TexAnimChunk val) {
        _texAnimChunk.add(val);
    }

    private List<GeosetChunk> _geosetChunks = new ArrayList<>();

    @Nonnull
    public List<GeosetChunk> getGeosetChunks() {
        return new ArrayList<>(_geosetChunks);
    }

    private void addGeosetChunk(@Nonnull GeosetChunk val) {
        _geosetChunks.add(val);
    }

    private List<GeosetAnimChunk> _geosetAnimChunks = new ArrayList<>();

    @Nonnull
    public List<GeosetAnimChunk> getGeosetAnimChunks() {
        return new ArrayList<>(_geosetAnimChunks);
    }

    private void addGeosetAnimChunk(@Nonnull GeosetAnimChunk val) {
        _geosetAnimChunks.add(val);
    }

    private List<BoneChunk> _boneChunks = new ArrayList<>();

    @Nonnull
    public List<BoneChunk> getBoneChunks() {
        return new ArrayList<>(_boneChunks);
    }

    private void addBoneChunk(@Nonnull BoneChunk val) {
        _boneChunks.add(val);
    }

    private List<LightChunk> _lightChunks = new ArrayList<>();

    @Nonnull
    public List<LightChunk> getLightChunks() {
        return new ArrayList<>(_lightChunks);
    }

    private void addLightChunk(@Nonnull LightChunk val) {
        _lightChunks.add(val);
    }

    private List<HelperChunk> _helperChunks = new ArrayList<>();

    @Nonnull
    public List<HelperChunk> getHelperChunks() {
        return new ArrayList<>(_helperChunks);
    }

    private void addHelperChunk(@Nonnull HelperChunk val) {
        _helperChunks.add(val);
    }

    private List<AttachmentChunk> _attachmentChunks = new ArrayList<>();

    @Nonnull
    public List<AttachmentChunk> getAttachmentChunks() {
        return new ArrayList<>(_attachmentChunks);
    }

    private void addAttachmentChunk(@Nonnull AttachmentChunk val) {
        _attachmentChunks.add(val);
    }

    private List<PivotPointChunk> _pivotPointChunks = new ArrayList<>();

    @Nonnull
    public List<PivotPointChunk> getPivotPointChunks() {
        return new ArrayList<>(_pivotPointChunks);
    }

    private void addPivotPointChunk(@Nonnull PivotPointChunk val) {
        _pivotPointChunks.add(val);
    }

    private List<ParticleEmitterChunk> _particleEmitterChunks = new ArrayList<>();

    @Nonnull
    public List<ParticleEmitterChunk> getParticleEmitterChunks() {
        return new ArrayList<>(_particleEmitterChunks);
    }

    private void addParticleEmitterChunk(@Nonnull ParticleEmitterChunk val) {
        _particleEmitterChunks.add(val);
    }

    private List<ParticleEmitter2Chunk> _particleEmitter2Chunks = new ArrayList<>();

    @Nonnull
    public List<ParticleEmitter2Chunk> getParticleEmitter2Chunks() {
        return new ArrayList<>(_particleEmitter2Chunks);
    }

    private void addParticleEmitter2Chunk(@Nonnull ParticleEmitter2Chunk val) {
        _particleEmitter2Chunks.add(val);
    }

    private List<RibbonEmitterChunk> _ribbonEmitterChunks = new ArrayList<>();

    @Nonnull
    public List<RibbonEmitterChunk> getRibbonEmitterChunks() {
        return new ArrayList<>(_ribbonEmitterChunks);
    }

    private void addRibbonEmitterChunk(@Nonnull RibbonEmitterChunk val) {
        _ribbonEmitterChunks.add(val);
    }

    private List<EventObjectChunk> _eventObjectChunks = new ArrayList<>();

    @Nonnull
    public List<EventObjectChunk> getEventObjectChunks() {
        return new ArrayList<>(_eventObjectChunks);
    }

    private void addEventObjectChunk(@Nonnull EventObjectChunk val) {
        _eventObjectChunks.add(val);
    }

    private List<CameraChunk> _cameraChunks = new ArrayList<>();

    @Nonnull
    public List<CameraChunk> getCameraChunks() {
        return new ArrayList<>(_cameraChunks);
    }

    private void addCameraChunk(@Nonnull CameraChunk val) {
        _cameraChunks.add(val);
    }

    private List<CollisionShapeChunk> _collisionShapeChunks = new ArrayList<>();

    @Nonnull
    public List<CollisionShapeChunk> getCollisionShapeChunks() {
        return new ArrayList<>(_collisionShapeChunks);
    }

    private void addCollisionShapeChunk(@Nonnull CollisionShapeChunk val) {
        _collisionShapeChunks.add(val);
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
        public void run() throws BinStream.StreamException;
    }

    private void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        Id startToken = stream.readId("startToken");

        if (!startToken.equals(TOKEN)) throw new IllegalArgumentException("invalid " + TOKEN + " startToken (" + startToken + ")");

        Map<Id, TokenHandler> _tokenMap = new LinkedHashMap<>();

        _tokenMap.put(VersionChunk.TOKEN, () -> addVersionChunk(new VersionChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(ModelInfoChunk.TOKEN, () -> addModelInfoChunk(new ModelInfoChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(SequenceChunk.TOKEN, () -> addSequenceChunk(new SequenceChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(GlobalSequenceChunk.TOKEN, () -> addGlobalSequenceChunk(new GlobalSequenceChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(TextureChunk.TOKEN, () -> addTextureChunk(new TextureChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(SoundChunk.TOKEN, () -> addSoundChunk(new SoundChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(MaterialChunk.TOKEN, () -> addMaterialChunk(new MaterialChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(TexAnimChunk.TOKEN, () -> addTextureAnimChunk(new TexAnimChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(GeosetChunk.TOKEN, () -> addGeosetChunk(new GeosetChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(GeosetAnimChunk.TOKEN, () -> addGeosetAnimChunk(new GeosetAnimChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(BoneChunk.TOKEN, () -> addBoneChunk(new BoneChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(LightChunk.TOKEN, () -> addLightChunk(new LightChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(HelperChunk.TOKEN, () -> addHelperChunk(new HelperChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(AttachmentChunk.TOKEN, () -> addAttachmentChunk(new AttachmentChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(PivotPointChunk.TOKEN, () -> addPivotPointChunk(new PivotPointChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(ParticleEmitterChunk.TOKEN, () -> addParticleEmitterChunk(new ParticleEmitterChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(ParticleEmitter2Chunk.TOKEN, () -> addParticleEmitter2Chunk(new ParticleEmitter2Chunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(RibbonEmitterChunk.TOKEN, () -> addRibbonEmitterChunk(new RibbonEmitterChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(EventObjectChunk.TOKEN, () -> addEventObjectChunk(new EventObjectChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(CameraChunk.TOKEN, () -> addCameraChunk(new CameraChunk(stream, EncodingFormat.MDX_0x0)));
        _tokenMap.put(CollisionShapeChunk.TOKEN, () -> addCollisionShapeChunk(new CollisionShapeChunk(stream, EncodingFormat.MDX_0x0)));

        while (!stream.eof()) {
            Id chunkToken = stream.readId("chunkToken");

            if (_tokenMap.containsKey(chunkToken)) {
                TokenHandler handler = _tokenMap.get(chunkToken);
                System.out.println("token " + chunkToken);
                stream.rewind(4);

                handler.run();
            } else {
                long size = stream.readUInt32("header_size");

                stream.skip(size);
            }
        }
    }

    private void write_0x0(@Nonnull Wc3BinOutputStream stream) {
        stream.writeId(TOKEN);

        stream.writeUInt32(_versionChunks.size());

        for (VersionChunk versionChunk : _versionChunks) {
            versionChunk.write(stream, EncodingFormat.MDX_0x0);
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

    private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
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

    private void write(@Nonnull Wc3BinOutputStream stream) {
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
}
