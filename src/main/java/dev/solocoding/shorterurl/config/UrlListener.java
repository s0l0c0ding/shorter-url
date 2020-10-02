package dev.solocoding.shorterurl.config;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import org.springframework.stereotype.Component;

import dev.solocoding.shorterurl.entity.Url;

@Component
public class UrlListener implements BeforeConvertCallback<Url> {

    @Override
    public Url onBeforeConvert(Url entity, String collection) {
        if (entity.getId() == null) {
            ObjectId id = new ObjectId();
            entity.setId(id);
            entity.setShortUrl(id.toHexString().substring(18, 24));
        }
        return entity;
    }
}
