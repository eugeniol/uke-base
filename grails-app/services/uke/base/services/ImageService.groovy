package uke.base.services

import java.awt.image.BufferedImage

import java.awt.AlphaComposite
import java.awt.RenderingHints

import javax.imageio.ImageIO
import uke.model.Dimension

import org.imgscalr.Scalr

class ImageService {

    static transactional = false
    static scope         = "singleton"


    BufferedImage resizeImageWithHint(BufferedImage originalImage, int width, int height) {
            return Scalr.resize(
            originalImage,
            Scalr.Method.QUALITY,
            Scalr.Mode.FIT_EXACT,
            width,
            height,
            Scalr.OP_ANTIALIAS);

        def dimension = new Dimension(width: originalImage.width, height: originalImage.height).resize(width, height)

        int type = originalImage.type == BufferedImage.TYPE_CUSTOM ?
            BufferedImage.TYPE_INT_ARGB : originalImage.type


        BufferedImage resizedImage = new BufferedImage(dimension.width, dimension.height, type)

        resizedImage.createGraphics().with {
            drawImage(originalImage, 0, 0, dimension.width, dimension.height, null)
            dispose()
            setComposite(AlphaComposite.Src)
            //setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
            //setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
            setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        }

        return resizedImage;
    }

    def handleUpload(List files) {
        files.collect { uploadedFile ->
            try {
                def file = uploadedFile.fileItem?.inputStream
/*
                ImageInputStream iis = ImageIO.createImageInputStream(file);
                Iterator<ImageReader> readers = ImageIO.getImageReaders(iis)
                if (readers.hasNext()) {

                    // pick the first available ImageReader
                    ImageReader reader = readers.next();

                    // attach source to the reader
                    reader.setInput(iis, true);

                    // read metadata of first image
                    IIOMetadata metadata = reader.getImageMetadata(0);

                    metadata.getMetadataFormatNames().each {
                        def n = metadata.getAsTree(it)
                        println(n)
                    }
                }
  */
                def im = ImageIO.read(file)

                im
            }
            catch (Exception ex) {
                println "Error al procesar la imagen: $ex"
            }
        }.findAll {it != null}
    }
}


