package uke

import uke.model.Dimension
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import javax.imageio.stream.ImageInputStream
import javax.imageio.ImageReader
import javax.imageio.metadata.IIOMetadata
import uke.model.ISearchable

class ImageAsset implements ISearchable {
    String id
    String caption
    byte[] imageBytes

    BufferedImage image

    @Delegate Dimension size = new Dimension()

    static transients = ['image', 'size', 'ratio']

    static mapping = {
        id generator: "uuid", column: "id", unique: "true"

    }

    static constraints = {
        importFrom Dimension
        caption nullable: true
        imageBytes maxSize: 20971520  // max 20 Mb
    }


    BufferedImage getImage() {
        if (image == null && imageBytes != null) {
            image = ImageIO.read(new ByteArrayInputStream(imageBytes))
        }
        return image

    }

    void setImage(BufferedImage image) {
        this.image = image
        if (image != null) {
            def baos = new ByteArrayOutputStream()
            ImageIO.write(image, "jpg", baos)
            baos.flush();
            imageBytes = baos.toByteArray();
            baos.close();

            size.width = image.width
            size.height = image.height
        }

    }

    String readAndDisplayMetadata(String fileName) {
        String str = ''
        try {

            ImageInputStream iis = ImageIO.createImageInputStream(new ByteArrayInputStream(imageBytes));
            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);


            if (readers.hasNext()) {

                // pick the first available ImageReader
                ImageReader reader = readers.next();

                // attach source to the reader
                reader.setInput(iis, true);

                // read metadata of first image
                IIOMetadata metadata = reader.getImageMetadata(0);

                String[] names = metadata.getMetadataFormatNames();
                int length = names.length;
                for (int i = 0; i < length; i++) {
                    System.out.println("Format name: " + names[i]);
                    def t = metadata.getAsTree(names[i])
                    str += t.toString()
                    println(t);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        str
    }

    Map toSearchableMap() {
        [
            id: id,
            width: width,
            height: height,
            ratio: ratio
        ]
    }
}
