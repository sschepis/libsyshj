/*
 * Copyright 2013 Google Inc.
 * Copyright 2014 Andreas Schildbach
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

import org.bitcoinj.core.Utils;
import org.spongycastle.util.encoders.Hex;

import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the Syscoin testnet, a separate public network that has
 * relaxed rules suitable for development and testing of applications and new
 * Syscoin versions.
 */

// TODO: fix params
public class SyscoinTestNet3Params extends AbstractSyscoinParams {
    public static final int TESTNET_MAJORITY_WINDOW = 1000;
    public static final int TESTNET_MAJORITY_REJECT_BLOCK_OUTDATED = 750;
    public static final int TESTNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 501;
    protected static final int DIFFICULTY_CHANGE_TARGET = 145000;

    public SyscoinTestNet3Params() {
        super(DIFFICULTY_CHANGE_TARGET);
        id = ID_SYS_TESTNET;

        packetMagic = 0xfcc1b7dc;

        maxTarget = Utils.decodeCompactBits(0x1e0fffffL);
        port = 18369;
        addressHeader = 65;
        p2shHeader = 196;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        dumpedPrivateKeyHeader = 239;
        genesisBlock.setTime(1524507866L);
        genesisBlock.setDifficultyTarget(0x1e0ffff0L);
        genesisBlock.setNonce(442226);
        spendableCoinbaseDepth = 30;
        subsidyDecreaseBlockCount = 525600;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("bb0a78264637406b6360aad926284d544d7049f45189db5664f3c4d07350559e"));
        alertSigningKey = Hex.decode("042756726da3c7ef515d89212ee1705023d14be389e25fe15611585661b9a20021908b2b80a3c7200a0139dd2b26946606aab0eef9aa7689a6dc2c7eee237fa834");

        majorityEnforceBlockUpgrade = TESTNET_MAJORITY_ENFORCE_BLOCK_UPGRADE;
        majorityRejectBlockOutdated = TESTNET_MAJORITY_REJECT_BLOCK_OUTDATED;
        majorityWindow = TESTNET_MAJORITY_WINDOW;

        dnsSeeds = new String[] {
            "testseed.jrn.me.uk"
        };
        // Note this is the same as the BIP32 testnet, as BIP44 makes HD wallets
        // chain agnostic. Syscoin mainnet has its own headers for legacy reasons.
        bip32HeaderPub = 0x043587CF;
        bip32HeaderPriv = 0x04358394;
    }

    private static SyscoinTestNet3Params instance;
    public static synchronized SyscoinTestNet3Params get() {
        if (instance == null) {
            instance = new SyscoinTestNet3Params();
        }
        return instance;
    }

    @Override
    public boolean allowMinDifficultyBlocks() {
        return true;
    }

    @Override
    public String getPaymentProtocolId() {
        // TODO: CHANGE ME
        return PAYMENT_PROTOCOL_ID_TESTNET;
    }

    @Override
    public boolean isTestNet() {
        return true;
    }
}