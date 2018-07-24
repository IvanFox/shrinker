package me.ivanlis.shrinker.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.ivanlis.shrinker.api.ApiLinkModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "link")
@EqualsAndHashCode(callSuper = false)
public class Link extends BaseEntity {

    @Indexed
    String shortUrl;

    String url;

    public ApiLinkModel getAsApiModel(){
        return ApiLinkModel.of(shortUrl, url);
    }

}
