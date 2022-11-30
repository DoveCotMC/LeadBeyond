package io.github.dovecotmc.leadbeyond.common.reg;

import com.tterrag.registrate.AbstractRegistrate;

public class LBRegistrate extends AbstractRegistrate<LBRegistrate> {
	/**
	 * Construct a new Registrate for the given mod ID.
	 *
	 * @param modid The mod ID for which objects will be registered
	 */
	protected LBRegistrate(String modid) {
		super(modid);
	}

	public static LBRegistrate create(String modid) {
		return new LBRegistrate(modid);
	}
}
