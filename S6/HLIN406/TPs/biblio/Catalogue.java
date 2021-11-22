// Merci Benoasticot

package biblio;

import java.util.HashMap;

public class Catalogue {
  private HashMap<String, NoticeBibliographique> notices;

  public Catalogue() {
    this.notices = new HashMap<String, NoticeBibliographique>();
  }

  public void addNotice(NoticeBibliographique notice) {
    this.notices.put(notice.getisbn(), notice);
  }
}