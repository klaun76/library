package sk.mrtn.library.client.fileupload;

import com.google.gwt.logging.client.LogConfiguration;
import elemental.client.Browser;
import elemental.dom.Element;
import elemental.events.Event;
import elemental.events.EventListener;
import elemental.html.File;
import elemental.html.FileList;
import elemental.html.InputElement;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by martinliptak on 10/09/16.
 */
public class FileUploadController implements EventListener {

    private InputElement inputElement;

    private static Logger LOG;
    static {
        if (LogConfiguration.loggingIsEnabled()) {
            LOG = Logger.getLogger(FileUploadController.class.getSimpleName());
            LOG.setLevel(Level.ALL);
        }
    }

    @Inject
    FileUploadController(

    ){}

    public FileUploadController initialize() {
        if (this.inputElement == null) {
            this.inputElement = Browser.getDocument().createInputElement();
            this.inputElement.setType("file");
            this.inputElement.setOnchange(this);
        }
        return this;
    }


    public Element asElement() {
        initialize();
        return this.inputElement;
    }

    @Override
    public void handleEvent(Event evt) {
        FileList files = this.inputElement.getFiles();
        if (files.length() == 1) {
            File file = files.item(0);
            LOG.fine(file.getName());
            LOG.fine("Size: "+file.getSize());
            LOG.fine("type: "+file.getType());
        }
    }
}
