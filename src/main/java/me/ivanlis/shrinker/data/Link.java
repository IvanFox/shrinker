package me.ivanlis.shrinker.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "link")
@EqualsAndHashCode(callSuper = true)
public class Link extends BaseEntity {

    @Indexed
    String shortLink;

    String link;

}
