package sk.mrtn.library.generator.device;

import com.google.gwt.core.ext.*;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import java.io.PrintWriter;
import java.util.Objects;

/**
 * DeviceTypeGenerator creates the implementation for
 * {@link sk.mrtn.library.client.device.IDeviceType} for each device.
 * look at Library.gwt.xml file and <define-property name="sk.mrtn.devicetype"
 * and next property-provider, which is javascript snippet
 * until now is only one generator that i've created so i hope i will not
 * delete it and recycle knowledge :-)
 * @author klaun
 */

public class DeviceTypeGenerator extends Generator {

    private static final String SK_MRTN_DEVICETYPE = "sk.mrtn.devicetype";
    @Override
    public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {
        final PropertyOracle propertyOracle = context.getPropertyOracle();
        final SelectionProperty deviceTypeProperty;
        try {
            deviceTypeProperty = propertyOracle.getSelectionProperty(logger, SK_MRTN_DEVICETYPE);
        } catch (BadPropertyValueException e) {
            logger.log(TreeLogger.Type.ERROR, "Unable to read " + SK_MRTN_DEVICETYPE, e);
            throw new UnableToCompleteException();
        }

        final JClassType type;
        try {
            type = context.getTypeOracle().getType(typeName);
        } catch (NotFoundException e) {
            logger.log(TreeLogger.Type.ERROR, "Unable to find  " + typeName + ".");
            throw new UnableToCompleteException();
        }

        String deviceType = deviceTypeProperty.getCurrentValue();
        String packageName = type.getPackage().getName();
        String simpleName = type.getSimpleSourceName() + capitalize(deviceType);
        String fullName = packageName + "." + simpleName;

        final PrintWriter printWriter = context.tryCreate(logger, packageName, simpleName);
        if (printWriter == null) {
            // already generated
            return fullName;
        }

        final ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(packageName, simpleName);
        composer.addImplementedInterface(typeName);
        final SourceWriter writer = composer.createSourceWriter(context, printWriter);

        writer.println("public boolean isPhone() {");
        writer.indent();
        writer.println("return " + (Objects.equals(deviceType, "phone")) + ";");
        writer.outdent();
        writer.println("}");

        writer.println("public boolean isTablet() {");
        writer.indent();
        writer.println("return " + (Objects.equals(deviceType, "tablet")) + ";");
        writer.outdent();
        writer.println("}");

        writer.println("public boolean isDesktop() {");
        writer.indent();
        writer.println("return " + (Objects.equals(deviceType, "desktop")) + ";");
        writer.outdent();
        writer.println("}");

        writer.commit(logger);

        return fullName;
    }

    private String capitalize(final String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
