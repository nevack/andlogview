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
package org.bitbucket.mlopatkin.utils.properties;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


class PointParser implements Parser<Point> {

    private static final String UNDEFINED = "undefined";

    // simple comma-separated pair x,y
    ListParser<Integer> internalListParser = new ListParser<Integer>(Parsers.integerParser);

    @Override
    public Point read(String value) {
        value = StringUtils.trimToNull(value);
        if (value == null || UNDEFINED.equalsIgnoreCase(value)) {
            return null;
        }
        List<Integer> coords = internalListParser.read(value);
        if (coords.size() != 2) {
            throw new IllegalArgumentException("Expecting two coords, found " + coords.size());
        }
        return new Point(coords.get(0), coords.get(1));
    }

    @Override
    public String write(Point value) {
        if (value == null) {
            return UNDEFINED;
        }
        return internalListParser.write(Arrays.asList(value.x, value.y));
    }

}