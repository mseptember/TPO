package zad1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil {
	public static void processDir(String dirName, String resultFileName) {
		Path path = Paths.get(dirName);
		Path output = Paths.get(System.getProperty("user.dir/") + resultFileName);

		if(Files.exists(output)) {
			try {
				Files.delete(output);
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		try {
			Files.walkFileTree(path, new FileVisitor<Path>() {
				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					RandomAccessFile input = new RandomAccessFile(file.toFile(), "r");
					FileChannel fileChannel = input.getChannel();
					FileChannel fileChannelOut = FileChannel.open(output, StandardOpenOption.CREATE, StandardOpenOption.WRITE,  StandardOpenOption.APPEND);

					ByteBuffer buf = ByteBuffer.allocate((int) fileChannel.size());
					int bytesRead = fileChannel.read(buf);
					buf.flip();

					CharBuffer charBuffer = Charset.forName("CP1250").decode(buf);
					buf = Charset.forName("UTF-8").encode(charBuffer);
					fileChannelOut.write(buf);
					fileChannelOut.close();
					fileChannel.close();

					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					return FileVisitResult.CONTINUE;
				}
			});
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
