package io.github.jzdayz.apache.vfs;

import com.google.common.io.ByteStreams;
import java.io.InputStream;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

public class Demo {

  public static void main(String[] args) throws Exception {
    try (
        StandardFileSystemManager fsManager = new StandardFileSystemManager()
    ) {
      fsManager.init();
      FileSystemOptions options = new FileSystemOptions();
      SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(options, false);
      FileObject file = fsManager
          .resolveFile("sftp://root:JKLjkl123@jzdayz.club/root/gitea.out", options);

      InputStream inputStream =
          file.getContent().getInputStream();

      byte[] bytes = ByteStreams.toByteArray(inputStream);

      System.out.println(new String(bytes));
    }

  }

  public static FileSystemOptions createDefaultOptions() throws FileSystemException {
    // Create SFTP options
    FileSystemOptions opts = new FileSystemOptions();

    // SSH Key checking
//        SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");

    /*
     * Using the following line will cause VFS to choose File System's Root
     * as VFS's root. If I wanted to use User's home as VFS's root then set
     * 2nd method parameter to "true"
     */
    // Root directory set to user home
    SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, false);

    // Timeout is count by Milliseconds
//        SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, 10000);

    return opts;
  }
}
