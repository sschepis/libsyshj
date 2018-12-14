/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.libdohj.params;

import org.bitcoinj.core.Sha256Hash;

import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the main Syscoin production network on which people trade
 * goods and services.
 */

// TODO: fix params
public class SyscoinMainNetParams extends AbstractSyscoinParams {
    public static final int MAINNET_MAJORITY_WINDOW = 2000;
    public static final int MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED = 1900;
    public static final int MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 1500;
    protected static final int DIFFICULTY_CHANGE_TARGET = 145000;

    public SyscoinMainNetParams() {
        super(DIFFICULTY_CHANGE_TARGET);
        dumpedPrivateKeyHeader = 128; // See chainparams.cpp SECRET_ADDRESS_SYS
        addressHeader = 63;
        p2shHeader = 5;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        port = 8369;
        packetMagic = 0xc0c0c0c0;
        // Note that while BIP44 makes HD wallets chain-agnostic, for legacy
        // reasons we use a Sys-specific header for main net. At some point
        // we'll add independent headers for BIP32 legacy and BIP44.
        bip32HeaderPub = 0x02facafd; //The 4 byte header that serializes in base58 to "dgub".
        bip32HeaderPriv =  0x02fac398; //The 4 byte header that serializes in base58 to "dgpv".
        genesisBlock.setDifficultyTarget(0x1e0ffff0L);
        genesisBlock.setTime(1525170117L);
        genesisBlock.setNonce(2559938L);
        id = ID_SYS_MAINNET;
        subsidyDecreaseBlockCount = 525600;
        spendableCoinbaseDepth = 100;

        // Note this is an SHA256 hash, not a Scrypt hash. Scrypt hashes are only
        // used in difficulty calculations.
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("6e5c08d6d2414435b294210266753b05a75f90e926dd5e6082306812622"),
                genesisHash);

        majorityEnforceBlockUpgrade = MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE;
        majorityRejectBlockOutdated = MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED;
        majorityWindow = MAINNET_MAJORITY_WINDOW;

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        checkpoints.put(    0, Sha256Hash.wrap("000006e5c08d6d2414435b294210266753b05a75f90e926dd5e6082306812622"));
        checkpoints.put(100000, Sha256Hash.wrap("28b16c3500a8ede47d2a2373f9a5f9a25f38273daf3b4da2ba0001ca80fe6c4b"));
        checkpoints.put(150000, Sha256Hash.wrap("8e1a7181aaa84885d741b803d18d283663d0b25f8763224281dcc09c4fa874c9"));
        checkpoints.put(170000, Sha256Hash.wrap("98f57b8ca0834c19edad1dd01ebe13809956ec35b57b5d0b237eb7fee0a59965"));
        checkpoints.put(170001, Sha256Hash.wrap("796579749c97a1bb0414457a7833ac9968b93987292ccda9481172ae548b1588"));
        checkpoints.put(171000, Sha256Hash.wrap("4f2a4785a18e693c3283cc2c66c9f2b93f3fd20b8ee958f23b7a9f30800d13d2"));
        checkpoints.put(176000, Sha256Hash.wrap("92025d074fdd503b0f0f4d4a11dcfbc8a57509d0a196bb400ebdb19556d61b0d"));
        checkpoints.put(307300, Sha256Hash.wrap("04cc9c52b4fe7a1c14494f99c72faa18822514b378f6cf8480da05679dd5be43"));

        dnsSeeds = new String[] {
                "seed1.syscoin.org",
                "seed2.syscoin.org",
                "seed3.syscoin.org"
        };
    }

    private static SyscoinMainNetParams instance;
    public static synchronized SyscoinMainNetParams get() {
        if (instance == null) {
            instance = new SyscoinMainNetParams();
        }
        return instance;
    }

    @Override
    public boolean allowMinDifficultyBlocks() {
        return false;
    }

    @Override
    public String getPaymentProtocolId() {
        return ID_SYS_MAINNET;
    }

    @Override
    public boolean isTestNet() {
        return false;
    }
}
