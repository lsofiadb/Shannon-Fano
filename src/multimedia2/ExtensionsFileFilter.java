package multimedia2;

import java.io.File;
import java.util.LinkedList;
import javax.swing.filechooser.FileFilter;

public class ExtensionsFileFilter extends FileFilter {
  private LinkedList<String> listExtensions = new LinkedList<>();
  
  public ExtensionsFileFilter(String[] extensions) {
    for (String fext : extensions)
      addExtension(fext); 
  }
  
  public boolean accept(File f) {
    if (f.isDirectory())
      return true; 
    String filename = f.getName();
    String fileext = filename.substring(filename.indexOf('.') + 1);
    for (String fext : this.listExtensions) {
      if (fext.compareTo(fileext) == 0)
        return true; 
    } 
    return false;
  }
  
  public String getDescription() {
    return "List accepted file extensions: " + this.listExtensions.toString();
  }
  
  public void addExtension(String ext) {
    if (!this.listExtensions.contains(ext))
      this.listExtensions.addFirst(ext); 
  }
}
