import java.util.zip.ZipOutputStream  
import java.util.zip.ZipEntry  
import java.nio.channels.FileChannel  
  
String zipFileName = "file.zip"  
String inputDir = "logs"  
  
ZipOutputStream zipFile = new ZipOutputStream(new FileOutputStream(zipFileName))  
new File(inputDir).eachFile() { file ->  
zipFile.putNextEntry(new ZipEntry(file.getName()))  
def buffer = new byte[1024]  
file.withInputStream { i ->  
def l = i.read(buffer)  
// check wether the file is empty  
if (l > 0) {  
zipFile.write(buffer, 0, l)  
}  
}  
zipFile.closeEntry()  
}  
zipFile.close()  

def getPi(value) {
    "value" + value
}

if(new File("/", 'a.txt').exists()) {
	new File("/", 'a.txt').eachLine { 
		line -> println line
	}
}

File file = new File("/", 'a.txt')
boolean result = file.delete()
println result
println getPi("haha")

def list = [5, 6, 7, 8]
assert list.get(2) == 7

