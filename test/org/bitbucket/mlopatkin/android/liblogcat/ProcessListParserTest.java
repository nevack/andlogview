/*
 * Copyright 2011 Mikhail Lopatkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.bitbucket.mlopatkin.android.liblogcat;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ProcessListParserTest {

    private static final String PS_LINE_1 = "root      1     0     244    228   fg  ffffffff 00000000 S /init";
    private static final String PS_LINE_TWO_WORDS_PNAME = "root      279   2     0      0     fg  ffffffff 00000000 S Two words";
    private static final String PS_NO_PCY_LINE = "root      12    2     0      0         ffffffff 00000000 S sync_supers";
    private static final String PS_NO_NAME_LINE = "root      626   2     0      0     fg  ffffffff 00000000 S ";
    private static final String PS_WCHAN_SYMBOL_LINE = "root      29392 2     0      0     fg  cpu_stoppe 00000000 S migration/3";
    private static final String PS_NO_WCHAN_LINE = "u0_a251   4851  216   884420 35064 bg             00000000 R com.mapswithme.maps.pro";

    @Test
    public void testParseProcessListLine() {
        assertTrue(ProcessListParser.parseProcessListLine(PS_LINE_1).matches());
    }

    @Test
    public void testParseProcessNameWithSpaces() {
        assertTrue(ProcessListParser.parseProcessListLine(PS_LINE_TWO_WORDS_PNAME).matches());
    }

    @Test
    public void testParseProcessListNoPCY() {
        assertTrue(ProcessListParser.parseProcessListLine(PS_NO_PCY_LINE).matches());
    }

    @Test
    public void testParseProcessListNoName() {
        assertTrue(ProcessListParser.parseProcessListLine(PS_NO_NAME_LINE).matches());
    }

    @Test
    public void testParseProcessListSymbolicWchan() throws Exception {
        assertTrue(ProcessListParser.parseProcessListLine(PS_WCHAN_SYMBOL_LINE).matches());
    }

    @Test
    public void testParseProcessListMissingWchan() throws Exception {
        assertTrue(ProcessListParser.parseProcessListLine(PS_NO_WCHAN_LINE).matches());
    }
}
