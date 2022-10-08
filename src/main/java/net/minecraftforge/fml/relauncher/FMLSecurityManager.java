package net.minecraftforge.fml.relauncher;

import java.security.Permission;

/**
 * A custom security manager stopping certain events from happening
 * unexpectedly.
 *
 * @author cpw 
 * @author pandaninjas
 *
 */
public final class FMLSecurityManager extends SecurityManager {
		
	public Class<?>[] getStackClasses() {
        return getClassContext();
    }
	
    @Override
    public void checkPermission(Permission perm)
    {
        String permName = perm.getName() != null ? perm.getName() : "missing";
        if (permName.startsWith("exitVM"))
        {
            Class<?>[] classContexts = getClassContext();
            String callingClass = classContexts.length > 3 ? classContexts[4].getName() : "none";
            String callingParent = classContexts.length > 4 ? classContexts[5].getName() : "none";
            // FML is allowed to call system exit and the Minecraft applet (from the quit button)
            if (!(callingClass.startsWith("net.minecraftforge.fml.")
                    || "net.minecraft.server.dedicated.ServerHangWatchdog$1".equals(callingClass)
                    || "net.minecraft.server.dedicated.ServerHangWatchdog".equals(callingClass)
                    || ( "net.minecraft.client.Minecraft".equals(callingClass) && "net.minecraft.client.Minecraft".equals(callingParent))
                    || ("net.minecraft.server.dedicated.DedicatedServer".equals(callingClass) && "net.minecraft.server.MinecraftServer".equals(callingParent)))
                    )
            {
                throw new ExitTrappedException();
            }
        }
        else if ("setSecurityManager".equals(permName))
        {
            throw new SecurityException("Cannot replace the FML security manager");
        } else if ("createSecurityManager".equals(permName)) {
        	for (Class<?> c: getClassContext()) {
        		if (c.getName().equals("javax.crypto.JceSecurityManager")) {
        			return; // jce is allowed to use its security manager
        		} else if (c.getName().equals("net.minecraftforge.fml.relauncher.FMLSecurityManager")) {
        			return;
        		}
        	}
        	throw new SecurityException("Cannot create a SecurityManager");
        }

    }

    @Override
    public void checkPermission(Permission perm, Object context)
    {
        this.checkPermission(perm);
    }

    @Override
    public void checkPackageAccess(String pkg) {
    	if (pkg.equals("net.minecraftforge.securesession")) {
    		if (getClassContext()[1].getName().equals("net.minecraft.util.Session")) {
    			return;
    		}
    		throw new SecurityException("Cannot touch the session!");
    	}
    }
    
    public static class ExitTrappedException extends SecurityException {
        private static final long serialVersionUID = 1L;
    }
}
