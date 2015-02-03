/*
 * Copyright 2015 Mikhail Lopatkin
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

package org.bitbucket.mlopatkin.android.logviewer.filters;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import org.bitbucket.mlopatkin.android.liblogcat.LogRecord;
import org.bitbucket.mlopatkin.android.liblogcat.LogRecordParser;
import org.bitbucket.mlopatkin.android.liblogcat.filters.LogRecordFilter;
import org.bitbucket.mlopatkin.android.liblogcat.filters.SingleTagFilter;

import java.util.Collections;

final class FilterCollectionTestUtils {
    private FilterCollectionTestUtils() {
    }

    public static final LogRecord RECORD1 = LogRecordParser.parseThreadTime(
            LogRecord.Buffer.UNKNOWN,
            "08-03 16:21:35.538    98   231 V AudioFlinger: start(4117), calling thread 172",
            Collections.<Integer, String>emptyMap());

    public static final LogRecord RECORD2 = LogRecordParser.parseThreadTime(
            LogRecord.Buffer.UNKNOWN,
            "08-03 16:21:35.538    98   231 V NotAudioFlinger: start(4117), calling thread 172",
            Collections.<Integer, String>emptyMap());

    public static final LogRecordFilter MATCH_FIRST = new SingleTagFilter("AudioFlinger");
    public static final LogRecordFilter MATCH_SECOND = new SingleTagFilter("NotAudioFlinger");

    public static final Predicate<LogRecord> MATCH_ALL = Predicates.alwaysTrue();
    public static final Predicate<LogRecord> MATCH_NONE = Predicates.alwaysFalse();
}