package slk;

import misc.ObjId;

public abstract class ObjSLK<Self extends SLK<Self, ObjIdType, ObjType>, ObjIdType extends ObjId, ObjType extends SLK.Obj<? extends ObjIdType>> extends SLK<Self, ObjIdType, ObjType> {

}
