package com.example.example.realm;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/15$ 17:43$
 * <p/>
 */
public class CustomMigration implements RealmMigration{
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if(oldVersion == 0 && newVersion == 1){
           RealmObjectSchema userScheme =  schema.get("User");
            userScheme.addField("money",String.class, FieldAttribute.REQUIRED)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            obj.set("money","11");
                        }
                    }).removeField("age");
            oldVersion++;
        }
    }
}
