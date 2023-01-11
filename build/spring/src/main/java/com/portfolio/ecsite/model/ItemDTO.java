package com.portfolio.ecsite.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ItemDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-01-11T14:37:19.193228200+09:00[Asia/Tokyo]")
public class ItemDTO {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("itemName")
  private String itemName;

  public ItemDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * 商品ID
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "商品ID", required = true)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ItemDTO itemName(String itemName) {
    this.itemName = itemName;
    return this;
  }

  /**
   * 商品名
   * @return itemName
  */
  @NotNull 
  @Schema(name = "itemName", description = "商品名", required = true)
  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemDTO itemDTO = (ItemDTO) o;
    return Objects.equals(this.id, itemDTO.id) &&
        Objects.equals(this.itemName, itemDTO.itemName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, itemName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    itemName: ").append(toIndentedString(itemName)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

