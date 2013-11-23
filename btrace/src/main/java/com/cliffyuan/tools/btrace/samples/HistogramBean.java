/*
 * Copyright 2008-2010 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

package com.cliffyuan.tools.btrace.samples;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This sample collects histogram of javax.swing.JComponets
 * created by traced app. The histogram is printed once
 * every 4 seconds. Also, this example exposes the trace class
 * as a JMX bean. After connecting BTrace to the target  
 * application, connect VisualVM or jconsole or any other 
 * JMX client to the same application.
 */
@BTrace public class HistogramBean { 
   // @Property exposes this field as MBean attribute
   @Property
   private static Map<String, AtomicInteger> histo = newHashMap();

    @OnMethod(
        clazz="javax.swing.JComponent",
        method="<init>"
    ) 
    public static void onnewObject(@Self Object obj) {
        String cn = name(classOf(obj));
        AtomicInteger ai = get(histo, cn);
        if (ai == null) {
            ai = newAtomicInteger(1);
            put(histo, cn, ai);
        } else {
            incrementAndGet(ai);
        }     
    }

    @OnTimer(4000) 
    public static void print() {
        if (size(histo) != 0) {
            printNumberMap("Component Histogram", histo);
        }
    }
}
