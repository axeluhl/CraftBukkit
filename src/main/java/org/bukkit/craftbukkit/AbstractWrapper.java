package org.bukkit.craftbukkit;

import java.lang.ref.WeakReference;

import net.minecraft.server.WorldServer;

/**
 * Wraps a Minecraft object of type {@code T}, keeping a weak reference to it,
 * and a strong reference to the {@link WorldServer}. Access to the referent
 * happens in the {@link #getHandle} method where the object needs to be
 * re-resolved starting from the {@link WorldServer} if the weak reference was
 * had its referent collected in the meantime.
 * <p>
 * 
 * Implementing subclasses have to use their constructor to store context data
 * such as, e.g., the {@link WorldServer} that provides the context to
 * {@link #retrieve} the {@code t} object equivalent again at a later point
 * after the {@code t} object itself was garbage collected.
 * <p>
 * 
 * @param <T>
 */
public abstract class AbstractWrapper<T> {
	private WeakReference<T> handle;
	
	protected AbstractWrapper(T t) {
		handle = new WeakReference<>(t);
	}

	/**
	 * Retrieves an object equivalent to the original {@code T} object passed to the constructor, based
	 * on the context data remembered during the call to {@link #rememberQueryData(Object)} that the
	 * constructor performs.
	 */
	protected abstract T retrieve();
	
	protected T getHandle() {
		T result = handle.get();
		if (result == null) {
			result = retrieve();
			handle = new WeakReference<>(result);
		}
		return result;
	}
}
