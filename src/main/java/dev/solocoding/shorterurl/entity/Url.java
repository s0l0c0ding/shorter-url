package dev.solocoding.shorterurl.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.solocoding.shorterurl.dto.UrlDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
public class Url {

    @Id
    private ObjectId id;
    private String fullUrl;
    @Indexed(unique = true)
    private String shortUrl;
    private long count;
    @Version
    private Long version;

    public Url (UrlDto url) {
        id =  url.getId() != null?  new ObjectId(url.getId()) : null;
        fullUrl = url.getFullUrl();
        shortUrl = url.getShortUrl();
        count = url.getCount();
    }
}
