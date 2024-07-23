package com.example.pgp.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.jcajce.JcaPGPPublicKeyRingCollection;

public class App {
	public static void main(String[] args) throws IOException, PGPException {
		PGPPublicKey key = readKey("/test.asc");
		long validSeconds = key.getValidSeconds();
		System.out.println(validSeconds);
	}

	private static PGPPublicKey readKey(String filename) throws IOException, PGPException {
		InputStream in = App.class.getResourceAsStream(filename);
		in = org.bouncycastle.openpgp.PGPUtil.getDecoderStream(in);

		JcaPGPPublicKeyRingCollection pgpPub = new JcaPGPPublicKeyRingCollection(in);
		in.close();

		PGPPublicKey key = null;
		Iterator<PGPPublicKeyRing> rIt = pgpPub.getKeyRings();
		while (key == null && rIt.hasNext()) {
			PGPPublicKeyRing kRing = rIt.next();
			Iterator<PGPPublicKey> kIt = kRing.getPublicKeys();
			while (key == null && kIt.hasNext()) {
				PGPPublicKey k = kIt.next();

				if (k.isEncryptionKey()) {
					key = k;
				}
			}
		}
		return key;
	}
}
