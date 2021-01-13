package hr.abc.psd2.model.cards;

import java.util.HashMap;
import java.util.Objects;

import javax.validation.Valid;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
@Schema(description = "A list of hyperlinks to be recognised by the TPP.  Type of links admitted in this response:   - \"download\": a link to a resource, where the transaction report might be downloaded from in    case where transaction reports have a huge size.  Remark: This feature shall only be used where camt-data is requested which has a huge size. ")

public class LinksDownload extends HashMap<String, HrefType>  {
  private @Valid HrefType download = null;

  /**
   **/
  public LinksDownload download(HrefType download) {
    this.download = download;
    return this;
  }

  
  public HrefType getDownload() {
    return download;
  }
  public void setDownload(HrefType download) {
    this.download = download;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LinksDownload _linksDownload = (LinksDownload) o;
    return Objects.equals(download, _linksDownload.download);
  }

  @Override
  public int hashCode() {
    return Objects.hash(download);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LinksDownload {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    download: ").append(toIndentedString(download)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
