package uke.base.services

import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.springframework.web.multipart.MultipartFile
import org.apache.commons.io.FilenameUtils
import javax.activation.MimetypesFileTypeMap
import org.apache.commons.io.FileUtils

import uke.model.FileUploadModel
import uke.model.FileContainer

/**
 * Created with IntelliJ IDEA.
 * User: elattanzio
 * Date: 03/07/12
 * Time: 10:54
 * To change this template use File | Settings | File Templates.
 */
class UploadService {
    static scope = "session"

    Map<String, FileContainer> fileContainers = [:]

    File tempDir

    File getTempDir() {
        def servletContext = ServletContextHolder.servletContext
        def webRootDir = servletContext.getRealPath("/")
        def userDir = new File(webRootDir, "/upload-data")
        userDir.mkdirs()
        tempDir = userDir
    }

    FileUploadModel[] upload(String containerId, List<MultipartFile> uploadedFiles) {
        def total = uploadedFiles.inject(0) { c, i -> c + i.size }
        def userDir = getTempDir()
        def data = uploadedFiles.collect { uploadedFile ->
            def ext = FilenameUtils.getExtension(uploadedFile.originalFilename)
            File file = File.createTempFile(FilenameUtils.removeExtension(uploadedFile.originalFilename) + '-',
                '.' + ext, userDir)
            uploadedFile.transferTo(file)
            // def contentTypes = new MimetypesFileTypeMap()
            // contentTypes.getContentType(it)

            new FileUploadModel(
                file: file,
                bytes: file.size(),
                name: uploadedFile.originalFilename,
                type: uploadedFile.contentType,
            )
        }

        getFileContainer(containerId).files += data

        return data
    }


    Boolean delete(String containerId, String id) {
        def container = getFileContainer(containerId)
        def file = container?.files?.find {it.file?.name == id }
        if (file) {
            ((File) file.file)?.delete()
            container.files -= file
            true
        }
        else
            false
    }


    FileUploadModel[] getFiles(String containerId) {
        getFileContainer(containerId).files
    }

    FileUploadModel[] transferTo(String containerId, File dest) {
        def fileContainer = fileContainers.remove(containerId)
        fileContainer?.files.each {
            it.file.renameTo new File(dest, it.file.name)
        }
    }

    private FileContainer getFileContainer(String containerId) {
        fileContainers[containerId] = fileContainers[containerId] ?: new FileContainer()
    }

    Object preload(String containerId, List<File> files) {
        def userDir = getTempDir()
        def contentTypes = new MimetypesFileTypeMap()
        def data = files.collect {
            def ext = FilenameUtils.getExtension(it.name)
            File file = File.createTempFile(FilenameUtils.removeExtension(it.name) + '-',
                '.' + ext, userDir)

            FileUtils.copyFile(it, file, true)

            new FileUploadModel(
                file: file,
                bytes: file.size(),
                name: it.name,
                type: contentTypes.getContentType(file)
            )
        }
        getFileContainer(containerId).files += data
        return data
    }
}
