package hr.abc.psd2.model.dto.ais;

import java.io.Serializable;
import java.util.Objects;

public class Href implements Serializable {

    private static final long serialVersionUID = 1L;
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Href)) return false;
        Href href1 = (Href) o;
        return Objects.equals(href, href1.href);
    }

    @Override
    public int hashCode() {
        return Objects.hash(href);
    }
}
