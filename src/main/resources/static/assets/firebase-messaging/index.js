(function () {
    const t = document.createElement("link").relList;
    if (t && t.supports && t.supports("modulepreload")) return;
    for (const o of document.querySelectorAll('link[rel="modulepreload"]')) r(o);
    new MutationObserver(o => {
        for (const i of o) if (i.type === "childList") for (const s of i.addedNodes) s.tagName === "LINK" && s.rel === "modulepreload" && r(s)
    }).observe(document, {childList: !0, subtree: !0});

    function n(o) {
        const i = {};
        return o.integrity && (i.integrity = o.integrity), o.referrerPolicy && (i.referrerPolicy = o.referrerPolicy), o.crossOrigin === "use-credentials" ? i.credentials = "include" : o.crossOrigin === "anonymous" ? i.credentials = "omit" : i.credentials = "same-origin", i
    }

    function r(o) {
        if (o.ep) return;
        o.ep = !0;
        const i = n(o);
        fetch(o.href, i)
    }
})();/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const Ue = function (e) {
    const t = [];
    let n = 0;
    for (let r = 0; r < e.length; r++) {
        let o = e.charCodeAt(r);
        o < 128 ? t[n++] = o : o < 2048 ? (t[n++] = o >> 6 | 192, t[n++] = o & 63 | 128) : (o & 64512) === 55296 && r + 1 < e.length && (e.charCodeAt(r + 1) & 64512) === 56320 ? (o = 65536 + ((o & 1023) << 10) + (e.charCodeAt(++r) & 1023), t[n++] = o >> 18 | 240, t[n++] = o >> 12 & 63 | 128, t[n++] = o >> 6 & 63 | 128, t[n++] = o & 63 | 128) : (t[n++] = o >> 12 | 224, t[n++] = o >> 6 & 63 | 128, t[n++] = o & 63 | 128)
    }
    return t
}, Mt = function (e) {
    const t = [];
    let n = 0, r = 0;
    for (; n < e.length;) {
        const o = e[n++];
        if (o < 128) t[r++] = String.fromCharCode(o); else if (o > 191 && o < 224) {
            const i = e[n++];
            t[r++] = String.fromCharCode((o & 31) << 6 | i & 63)
        } else if (o > 239 && o < 365) {
            const i = e[n++], s = e[n++], a = e[n++],
                c = ((o & 7) << 18 | (i & 63) << 12 | (s & 63) << 6 | a & 63) - 65536;
            t[r++] = String.fromCharCode(55296 + (c >> 10)), t[r++] = String.fromCharCode(56320 + (c & 1023))
        } else {
            const i = e[n++], s = e[n++];
            t[r++] = String.fromCharCode((o & 15) << 12 | (i & 63) << 6 | s & 63)
        }
    }
    return t.join("")
}, Ke = {
    byteToCharMap_: null,
    charToByteMap_: null,
    byteToCharMapWebSafe_: null,
    charToByteMapWebSafe_: null,
    ENCODED_VALS_BASE: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789",
    get ENCODED_VALS() {
        return this.ENCODED_VALS_BASE + "+/="
    },
    get ENCODED_VALS_WEBSAFE() {
        return this.ENCODED_VALS_BASE + "-_."
    },
    HAS_NATIVE_SUPPORT: typeof atob == "function",
    encodeByteArray(e, t) {
        if (!Array.isArray(e)) throw Error("encodeByteArray takes an array as a parameter");
        this.init_();
        const n = t ? this.byteToCharMapWebSafe_ : this.byteToCharMap_, r = [];
        for (let o = 0; o < e.length; o += 3) {
            const i = e[o], s = o + 1 < e.length, a = s ? e[o + 1] : 0, c = o + 2 < e.length, u = c ? e[o + 2] : 0,
                d = i >> 2, A = (i & 3) << 4 | a >> 4;
            let O = (a & 15) << 2 | u >> 6, N = u & 63;
            c || (N = 64, s || (O = 64)), r.push(n[d], n[A], n[O], n[N])
        }
        return r.join("")
    },
    encodeString(e, t) {
        return this.HAS_NATIVE_SUPPORT && !t ? btoa(e) : this.encodeByteArray(Ue(e), t)
    },
    decodeString(e, t) {
        return this.HAS_NATIVE_SUPPORT && !t ? atob(e) : Mt(this.decodeStringToByteArray(e, t))
    },
    decodeStringToByteArray(e, t) {
        this.init_();
        const n = t ? this.charToByteMapWebSafe_ : this.charToByteMap_, r = [];
        for (let o = 0; o < e.length;) {
            const i = n[e.charAt(o++)], a = o < e.length ? n[e.charAt(o)] : 0;
            ++o;
            const u = o < e.length ? n[e.charAt(o)] : 64;
            ++o;
            const A = o < e.length ? n[e.charAt(o)] : 64;
            if (++o, i == null || a == null || u == null || A == null) throw new Pt;
            const O = i << 2 | a >> 4;
            if (r.push(O), u !== 64) {
                const N = a << 4 & 240 | u >> 2;
                if (r.push(N), A !== 64) {
                    const Bt = u << 6 & 192 | A;
                    r.push(Bt)
                }
            }
        }
        return r
    },
    init_() {
        if (!this.byteToCharMap_) {
            this.byteToCharMap_ = {}, this.charToByteMap_ = {}, this.byteToCharMapWebSafe_ = {}, this.charToByteMapWebSafe_ = {};
            for (let e = 0; e < this.ENCODED_VALS.length; e++) this.byteToCharMap_[e] = this.ENCODED_VALS.charAt(e), this.charToByteMap_[this.byteToCharMap_[e]] = e, this.byteToCharMapWebSafe_[e] = this.ENCODED_VALS_WEBSAFE.charAt(e), this.charToByteMapWebSafe_[this.byteToCharMapWebSafe_[e]] = e, e >= this.ENCODED_VALS_BASE.length && (this.charToByteMap_[this.ENCODED_VALS_WEBSAFE.charAt(e)] = e, this.charToByteMapWebSafe_[this.ENCODED_VALS.charAt(e)] = e)
        }
    }
};

class Pt extends Error {
    constructor() {
        super(...arguments), this.name = "DecodeBase64StringError"
    }
}

const Rt = function (e) {
    const t = Ue(e);
    return Ke.encodeByteArray(t, !0)
}, We = function (e) {
    return Rt(e).replace(/\./g, "")
}, $t = function (e) {
    try {
        return Ke.decodeString(e, !0)
    } catch (t) {
        console.error("base64Decode failed: ", t)
    }
    return null
};

/**
 * @license
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function Lt() {
    if (typeof self < "u") return self;
    if (typeof window < "u") return window;
    if (typeof global < "u") return global;
    throw new Error("Unable to locate global object.")
}

/**
 * @license
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const Ft = () => Lt().__FIREBASE_DEFAULTS__, xt = () => {
    if (typeof process > "u" || typeof process.env > "u") return;
    const e = {}.__FIREBASE_DEFAULTS__;
    if (e) return JSON.parse(e)
}, jt = () => {
    if (typeof document > "u") return;
    let e;
    try {
        e = document.cookie.match(/__FIREBASE_DEFAULTS__=([^;]+)/)
    } catch {
        return
    }
    const t = e && $t(e[1]);
    return t && JSON.parse(t)
}, Ht = () => {
    try {
        return Ft() || xt() || jt()
    } catch (e) {
        console.info(`Unable to get __FIREBASE_DEFAULTS__ due to: ${e}`);
        return
    }
}, qe = () => {
    var e;
    return (e = Ht()) === null || e === void 0 ? void 0 : e.config
};

/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class Vt {
    constructor() {
        this.reject = () => {
        }, this.resolve = () => {
        }, this.promise = new Promise((t, n) => {
            this.resolve = t, this.reject = n
        })
    }

    wrapCallback(t) {
        return (n, r) => {
            n ? this.reject(n) : this.resolve(r), typeof t == "function" && (this.promise.catch(() => {
            }), t.length === 1 ? t(n) : t(n, r))
        }
    }
}

function ze() {
    try {
        return typeof indexedDB == "object"
    } catch {
        return !1
    }
}

function Ge() {
    return new Promise((e, t) => {
        try {
            let n = !0;
            const r = "validate-browser-context-for-indexeddb-analytics-module", o = self.indexedDB.open(r);
            o.onsuccess = () => {
                o.result.close(), n || self.indexedDB.deleteDatabase(r), e(!0)
            }, o.onupgradeneeded = () => {
                n = !1
            }, o.onerror = () => {
                var i;
                t(((i = o.error) === null || i === void 0 ? void 0 : i.message) || "")
            }
        } catch (n) {
            t(n)
        }
    })
}

function Ut() {
    return !(typeof navigator > "u" || !navigator.cookieEnabled)
}

/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const Kt = "FirebaseError";

class D extends Error {
    constructor(t, n, r) {
        super(n), this.code = t, this.customData = r, this.name = Kt, Object.setPrototypeOf(this, D.prototype), Error.captureStackTrace && Error.captureStackTrace(this, F.prototype.create)
    }
}

class F {
    constructor(t, n, r) {
        this.service = t, this.serviceName = n, this.errors = r
    }

    create(t, ...n) {
        const r = n[0] || {}, o = `${this.service}/${t}`, i = this.errors[t], s = i ? Wt(i, r) : "Error",
            a = `${this.serviceName}: ${s} (${o}).`;
        return new D(o, a, r)
    }
}

function Wt(e, t) {
    return e.replace(qt, (n, r) => {
        const o = t[r];
        return o != null ? String(o) : `<${r}?>`
    })
}

const qt = /\{\$([^}]+)}/g;

function te(e, t) {
    if (e === t) return !0;
    const n = Object.keys(e), r = Object.keys(t);
    for (const o of n) {
        if (!r.includes(o)) return !1;
        const i = e[o], s = t[o];
        if (Se(i) && Se(s)) {
            if (!te(i, s)) return !1
        } else if (i !== s) return !1
    }
    for (const o of r) if (!n.includes(o)) return !1;
    return !0
}

function Se(e) {
    return e !== null && typeof e == "object"
}

/**
 * @license
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function x(e) {
    return e && e._delegate ? e._delegate : e
}

class w {
    constructor(t, n, r) {
        this.name = t, this.instanceFactory = n, this.type = r, this.multipleInstances = !1, this.serviceProps = {}, this.instantiationMode = "LAZY", this.onInstanceCreated = null
    }

    setInstantiationMode(t) {
        return this.instantiationMode = t, this
    }

    setMultipleInstances(t) {
        return this.multipleInstances = t, this
    }

    setServiceProps(t) {
        return this.serviceProps = t, this
    }

    setInstanceCreatedCallback(t) {
        return this.onInstanceCreated = t, this
    }
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const y = "[DEFAULT]";

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class zt {
    constructor(t, n) {
        this.name = t, this.container = n, this.component = null, this.instances = new Map, this.instancesDeferred = new Map, this.instancesOptions = new Map, this.onInitCallbacks = new Map
    }

    get(t) {
        const n = this.normalizeInstanceIdentifier(t);
        if (!this.instancesDeferred.has(n)) {
            const r = new Vt;
            if (this.instancesDeferred.set(n, r), this.isInitialized(n) || this.shouldAutoInitialize()) try {
                const o = this.getOrInitializeService({instanceIdentifier: n});
                o && r.resolve(o)
            } catch {
            }
        }
        return this.instancesDeferred.get(n).promise
    }

    getImmediate(t) {
        var n;
        const r = this.normalizeInstanceIdentifier(t == null ? void 0 : t.identifier),
            o = (n = t == null ? void 0 : t.optional) !== null && n !== void 0 ? n : !1;
        if (this.isInitialized(r) || this.shouldAutoInitialize()) try {
            return this.getOrInitializeService({instanceIdentifier: r})
        } catch (i) {
            if (o) return null;
            throw i
        } else {
            if (o) return null;
            throw Error(`Service ${this.name} is not available`)
        }
    }

    getComponent() {
        return this.component
    }

    setComponent(t) {
        if (t.name !== this.name) throw Error(`Mismatching Component ${t.name} for Provider ${this.name}.`);
        if (this.component) throw Error(`Component for ${this.name} has already been provided`);
        if (this.component = t, !!this.shouldAutoInitialize()) {
            if (Jt(t)) try {
                this.getOrInitializeService({instanceIdentifier: y})
            } catch {
            }
            for (const [n, r] of this.instancesDeferred.entries()) {
                const o = this.normalizeInstanceIdentifier(n);
                try {
                    const i = this.getOrInitializeService({instanceIdentifier: o});
                    r.resolve(i)
                } catch {
                }
            }
        }
    }

    clearInstance(t = y) {
        this.instancesDeferred.delete(t), this.instancesOptions.delete(t), this.instances.delete(t)
    }

    async delete() {
        const t = Array.from(this.instances.values());
        await Promise.all([...t.filter(n => "INTERNAL" in n).map(n => n.INTERNAL.delete()), ...t.filter(n => "_delete" in n).map(n => n._delete())])
    }

    isComponentSet() {
        return this.component != null
    }

    isInitialized(t = y) {
        return this.instances.has(t)
    }

    getOptions(t = y) {
        return this.instancesOptions.get(t) || {}
    }

    initialize(t = {}) {
        const {options: n = {}} = t, r = this.normalizeInstanceIdentifier(t.instanceIdentifier);
        if (this.isInitialized(r)) throw Error(`${this.name}(${r}) has already been initialized`);
        if (!this.isComponentSet()) throw Error(`Component ${this.name} has not been registered yet`);
        const o = this.getOrInitializeService({instanceIdentifier: r, options: n});
        for (const [i, s] of this.instancesDeferred.entries()) {
            const a = this.normalizeInstanceIdentifier(i);
            r === a && s.resolve(o)
        }
        return o
    }

    onInit(t, n) {
        var r;
        const o = this.normalizeInstanceIdentifier(n),
            i = (r = this.onInitCallbacks.get(o)) !== null && r !== void 0 ? r : new Set;
        i.add(t), this.onInitCallbacks.set(o, i);
        const s = this.instances.get(o);
        return s && t(s, o), () => {
            i.delete(t)
        }
    }

    invokeOnInitCallbacks(t, n) {
        const r = this.onInitCallbacks.get(n);
        if (r) for (const o of r) try {
            o(t, n)
        } catch {
        }
    }

    getOrInitializeService({instanceIdentifier: t, options: n = {}}) {
        let r = this.instances.get(t);
        if (!r && this.component && (r = this.component.instanceFactory(this.container, {
            instanceIdentifier: Gt(t),
            options: n
        }), this.instances.set(t, r), this.instancesOptions.set(t, n), this.invokeOnInitCallbacks(r, t), this.component.onInstanceCreated)) try {
            this.component.onInstanceCreated(this.container, t, r)
        } catch {
        }
        return r || null
    }

    normalizeInstanceIdentifier(t = y) {
        return this.component ? this.component.multipleInstances ? t : y : t
    }

    shouldAutoInitialize() {
        return !!this.component && this.component.instantiationMode !== "EXPLICIT"
    }
}

function Gt(e) {
    return e === y ? void 0 : e
}

function Jt(e) {
    return e.instantiationMode === "EAGER"
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class Yt {
    constructor(t) {
        this.name = t, this.providers = new Map
    }

    addComponent(t) {
        const n = this.getProvider(t.name);
        if (n.isComponentSet()) throw new Error(`Component ${t.name} has already been registered with ${this.name}`);
        n.setComponent(t)
    }

    addOrOverwriteComponent(t) {
        this.getProvider(t.name).isComponentSet() && this.providers.delete(t.name), this.addComponent(t)
    }

    getProvider(t) {
        if (this.providers.has(t)) return this.providers.get(t);
        const n = new zt(t, this);
        return this.providers.set(t, n), n
    }

    getProviders() {
        return Array.from(this.providers.values())
    }
}

/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */var l;
(function (e) {
    e[e.DEBUG = 0] = "DEBUG", e[e.VERBOSE = 1] = "VERBOSE", e[e.INFO = 2] = "INFO", e[e.WARN = 3] = "WARN", e[e.ERROR = 4] = "ERROR", e[e.SILENT = 5] = "SILENT"
})(l || (l = {}));
const Xt = {debug: l.DEBUG, verbose: l.VERBOSE, info: l.INFO, warn: l.WARN, error: l.ERROR, silent: l.SILENT},
    Qt = l.INFO, Zt = {[l.DEBUG]: "log", [l.VERBOSE]: "log", [l.INFO]: "info", [l.WARN]: "warn", [l.ERROR]: "error"},
    en = (e, t, ...n) => {
        if (t < e.logLevel) return;
        const r = new Date().toISOString(), o = Zt[t];
        if (o) console[o](`[${r}]  ${e.name}:`, ...n); else throw new Error(`Attempted to log a message with an invalid logType (value: ${t})`)
    };

class tn {
    constructor(t) {
        this.name = t, this._logLevel = Qt, this._logHandler = en, this._userLogHandler = null
    }

    get logLevel() {
        return this._logLevel
    }

    set logLevel(t) {
        if (!(t in l)) throw new TypeError(`Invalid value "${t}" assigned to \`logLevel\``);
        this._logLevel = t
    }

    setLogLevel(t) {
        this._logLevel = typeof t == "string" ? Xt[t] : t
    }

    get logHandler() {
        return this._logHandler
    }

    set logHandler(t) {
        if (typeof t != "function") throw new TypeError("Value assigned to `logHandler` must be a function");
        this._logHandler = t
    }

    get userLogHandler() {
        return this._userLogHandler
    }

    set userLogHandler(t) {
        this._userLogHandler = t
    }

    debug(...t) {
        this._userLogHandler && this._userLogHandler(this, l.DEBUG, ...t), this._logHandler(this, l.DEBUG, ...t)
    }

    log(...t) {
        this._userLogHandler && this._userLogHandler(this, l.VERBOSE, ...t), this._logHandler(this, l.VERBOSE, ...t)
    }

    info(...t) {
        this._userLogHandler && this._userLogHandler(this, l.INFO, ...t), this._logHandler(this, l.INFO, ...t)
    }

    warn(...t) {
        this._userLogHandler && this._userLogHandler(this, l.WARN, ...t), this._logHandler(this, l.WARN, ...t)
    }

    error(...t) {
        this._userLogHandler && this._userLogHandler(this, l.ERROR, ...t), this._logHandler(this, l.ERROR, ...t)
    }
}

const nn = (e, t) => t.some(n => e instanceof n);
let Te, De;

function rn() {
    return Te || (Te = [IDBDatabase, IDBObjectStore, IDBIndex, IDBCursor, IDBTransaction])
}

function on() {
    return De || (De = [IDBCursor.prototype.advance, IDBCursor.prototype.continue, IDBCursor.prototype.continuePrimaryKey])
}

const Je = new WeakMap, ne = new WeakMap, Ye = new WeakMap, V = new WeakMap, de = new WeakMap;

function sn(e) {
    const t = new Promise((n, r) => {
        const o = () => {
            e.removeEventListener("success", i), e.removeEventListener("error", s)
        }, i = () => {
            n(p(e.result)), o()
        }, s = () => {
            r(e.error), o()
        };
        e.addEventListener("success", i), e.addEventListener("error", s)
    });
    return t.then(n => {
        n instanceof IDBCursor && Je.set(n, e)
    }).catch(() => {
    }), de.set(t, e), t
}

function an(e) {
    if (ne.has(e)) return;
    const t = new Promise((n, r) => {
        const o = () => {
            e.removeEventListener("complete", i), e.removeEventListener("error", s), e.removeEventListener("abort", s)
        }, i = () => {
            n(), o()
        }, s = () => {
            r(e.error || new DOMException("AbortError", "AbortError")), o()
        };
        e.addEventListener("complete", i), e.addEventListener("error", s), e.addEventListener("abort", s)
    });
    ne.set(e, t)
}

let re = {
    get(e, t, n) {
        if (e instanceof IDBTransaction) {
            if (t === "done") return ne.get(e);
            if (t === "objectStoreNames") return e.objectStoreNames || Ye.get(e);
            if (t === "store") return n.objectStoreNames[1] ? void 0 : n.objectStore(n.objectStoreNames[0])
        }
        return p(e[t])
    }, set(e, t, n) {
        return e[t] = n, !0
    }, has(e, t) {
        return e instanceof IDBTransaction && (t === "done" || t === "store") ? !0 : t in e
    }
};

function cn(e) {
    re = e(re)
}

function un(e) {
    return e === IDBDatabase.prototype.transaction && !("objectStoreNames" in IDBTransaction.prototype) ? function (t, ...n) {
        const r = e.call(U(this), t, ...n);
        return Ye.set(r, t.sort ? t.sort() : [t]), p(r)
    } : on().includes(e) ? function (...t) {
        return e.apply(U(this), t), p(Je.get(this))
    } : function (...t) {
        return p(e.apply(U(this), t))
    }
}

function dn(e) {
    return typeof e == "function" ? un(e) : (e instanceof IDBTransaction && an(e), nn(e, rn()) ? new Proxy(e, re) : e)
}

function p(e) {
    if (e instanceof IDBRequest) return sn(e);
    if (V.has(e)) return V.get(e);
    const t = dn(e);
    return t !== e && (V.set(e, t), de.set(t, e)), t
}

const U = e => de.get(e);

function le(e, t, {blocked: n, upgrade: r, blocking: o, terminated: i} = {}) {
    const s = indexedDB.open(e, t), a = p(s);
    return r && s.addEventListener("upgradeneeded", c => {
        r(p(s.result), c.oldVersion, c.newVersion, p(s.transaction), c)
    }), n && s.addEventListener("blocked", c => n(c.oldVersion, c.newVersion, c)), a.then(c => {
        i && c.addEventListener("close", () => i()), o && c.addEventListener("versionchange", u => o(u.oldVersion, u.newVersion, u))
    }).catch(() => {
    }), a
}

function K(e, {blocked: t} = {}) {
    const n = indexedDB.deleteDatabase(e);
    return t && n.addEventListener("blocked", r => t(r.oldVersion, r)), p(n).then(() => {
    })
}

const ln = ["get", "getKey", "getAll", "getAllKeys", "count"], fn = ["put", "add", "delete", "clear"], W = new Map;

function Ae(e, t) {
    if (!(e instanceof IDBDatabase && !(t in e) && typeof t == "string")) return;
    if (W.get(t)) return W.get(t);
    const n = t.replace(/FromIndex$/, ""), r = t !== n, o = fn.includes(n);
    if (!(n in (r ? IDBIndex : IDBObjectStore).prototype) || !(o || ln.includes(n))) return;
    const i = async function (s, ...a) {
        const c = this.transaction(s, o ? "readwrite" : "readonly");
        let u = c.store;
        return r && (u = u.index(a.shift())), (await Promise.all([u[n](...a), o && c.done]))[0]
    };
    return W.set(t, i), i
}

cn(e => ({...e, get: (t, n, r) => Ae(t, n) || e.get(t, n, r), has: (t, n) => !!Ae(t, n) || e.has(t, n)}));

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class hn {
    constructor(t) {
        this.container = t
    }

    getPlatformInfoString() {
        return this.container.getProviders().map(n => {
            if (pn(n)) {
                const r = n.getImmediate();
                return `${r.library}/${r.version}`
            } else return null
        }).filter(n => n).join(" ")
    }
}

function pn(e) {
    const t = e.getComponent();
    return (t == null ? void 0 : t.type) === "VERSION"
}

const oe = "@firebase/app", Ce = "0.9.24";
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const E = new tn("@firebase/app"), gn = "@firebase/app-compat", mn = "@firebase/analytics-compat",
    bn = "@firebase/analytics", wn = "@firebase/app-check-compat", yn = "@firebase/app-check", In = "@firebase/auth",
    En = "@firebase/auth-compat", vn = "@firebase/database", _n = "@firebase/database-compat",
    Sn = "@firebase/functions", Tn = "@firebase/functions-compat", Dn = "@firebase/installations",
    An = "@firebase/installations-compat", Cn = "@firebase/messaging", kn = "@firebase/messaging-compat",
    On = "@firebase/performance", Nn = "@firebase/performance-compat", Bn = "@firebase/remote-config",
    Mn = "@firebase/remote-config-compat", Pn = "@firebase/storage", Rn = "@firebase/storage-compat",
    $n = "@firebase/firestore", Ln = "@firebase/firestore-compat", Fn = "firebase";
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const ie = "[DEFAULT]", xn = {
    [oe]: "fire-core",
    [gn]: "fire-core-compat",
    [bn]: "fire-analytics",
    [mn]: "fire-analytics-compat",
    [yn]: "fire-app-check",
    [wn]: "fire-app-check-compat",
    [In]: "fire-auth",
    [En]: "fire-auth-compat",
    [vn]: "fire-rtdb",
    [_n]: "fire-rtdb-compat",
    [Sn]: "fire-fn",
    [Tn]: "fire-fn-compat",
    [Dn]: "fire-iid",
    [An]: "fire-iid-compat",
    [Cn]: "fire-fcm",
    [kn]: "fire-fcm-compat",
    [On]: "fire-perf",
    [Nn]: "fire-perf-compat",
    [Bn]: "fire-rc",
    [Mn]: "fire-rc-compat",
    [Pn]: "fire-gcs",
    [Rn]: "fire-gcs-compat",
    [$n]: "fire-fst",
    [Ln]: "fire-fst-compat",
    "fire-js": "fire-js",
    [Fn]: "fire-js-all"
};
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const B = new Map, se = new Map;

function jn(e, t) {
    try {
        e.container.addComponent(t)
    } catch (n) {
        E.debug(`Component ${t.name} failed to register with FirebaseApp ${e.name}`, n)
    }
}

function v(e) {
    const t = e.name;
    if (se.has(t)) return E.debug(`There were multiple attempts to register component ${t}.`), !1;
    se.set(t, e);
    for (const n of B.values()) jn(n, e);
    return !0
}

function fe(e, t) {
    const n = e.container.getProvider("heartbeat").getImmediate({optional: !0});
    return n && n.triggerHeartbeat(), e.container.getProvider(t)
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const Hn = {
    "no-app": "No Firebase App '{$appName}' has been created - call initializeApp() first",
    "bad-app-name": "Illegal App name: '{$appName}",
    "duplicate-app": "Firebase App named '{$appName}' already exists with different options or config",
    "app-deleted": "Firebase App named '{$appName}' already deleted",
    "no-options": "Need to provide options, when not being deployed to hosting via source.",
    "invalid-app-argument": "firebase.{$appName}() takes either no argument or a Firebase App instance.",
    "invalid-log-argument": "First argument to `onLog` must be null or a function.",
    "idb-open": "Error thrown when opening IndexedDB. Original error: {$originalErrorMessage}.",
    "idb-get": "Error thrown when reading from IndexedDB. Original error: {$originalErrorMessage}.",
    "idb-set": "Error thrown when writing to IndexedDB. Original error: {$originalErrorMessage}.",
    "idb-delete": "Error thrown when deleting from IndexedDB. Original error: {$originalErrorMessage}."
}, g = new F("app", "Firebase", Hn);

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class Vn {
    constructor(t, n, r) {
        this._isDeleted = !1, this._options = Object.assign({}, t), this._config = Object.assign({}, n), this._name = n.name, this._automaticDataCollectionEnabled = n.automaticDataCollectionEnabled, this._container = r, this.container.addComponent(new w("app", () => this, "PUBLIC"))
    }

    get automaticDataCollectionEnabled() {
        return this.checkDestroyed(), this._automaticDataCollectionEnabled
    }

    set automaticDataCollectionEnabled(t) {
        this.checkDestroyed(), this._automaticDataCollectionEnabled = t
    }

    get name() {
        return this.checkDestroyed(), this._name
    }

    get options() {
        return this.checkDestroyed(), this._options
    }

    get config() {
        return this.checkDestroyed(), this._config
    }

    get container() {
        return this._container
    }

    get isDeleted() {
        return this._isDeleted
    }

    set isDeleted(t) {
        this._isDeleted = t
    }

    checkDestroyed() {
        if (this.isDeleted) throw g.create("app-deleted", {appName: this._name})
    }
}

function Xe(e, t = {}) {
    let n = e;
    typeof t != "object" && (t = {name: t});
    const r = Object.assign({name: ie, automaticDataCollectionEnabled: !1}, t), o = r.name;
    if (typeof o != "string" || !o) throw g.create("bad-app-name", {appName: String(o)});
    if (n || (n = qe()), !n) throw g.create("no-options");
    const i = B.get(o);
    if (i) {
        if (te(n, i.options) && te(r, i.config)) return i;
        throw g.create("duplicate-app", {appName: o})
    }
    const s = new Yt(o);
    for (const c of se.values()) s.addComponent(c);
    const a = new Vn(n, r, s);
    return B.set(o, a), a
}

function Un(e = ie) {
    const t = B.get(e);
    if (!t && e === ie && qe()) return Xe();
    if (!t) throw g.create("no-app", {appName: e});
    return t
}

function m(e, t, n) {
    var r;
    let o = (r = xn[e]) !== null && r !== void 0 ? r : e;
    n && (o += `-${n}`);
    const i = o.match(/\s|\//), s = t.match(/\s|\//);
    if (i || s) {
        const a = [`Unable to register library "${o}" with version "${t}":`];
        i && a.push(`library name "${o}" contains illegal characters (whitespace or "/")`), i && s && a.push("and"), s && a.push(`version name "${t}" contains illegal characters (whitespace or "/")`), E.warn(a.join(" "));
        return
    }
    v(new w(`${o}-version`, () => ({library: o, version: t}), "VERSION"))
}

/**
 * @license
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const Kn = "firebase-heartbeat-database", Wn = 1, C = "firebase-heartbeat-store";
let q = null;

function Qe() {
    return q || (q = le(Kn, Wn, {
        upgrade: (e, t) => {
            switch (t) {
                case 0:
                    e.createObjectStore(C)
            }
        }
    }).catch(e => {
        throw g.create("idb-open", {originalErrorMessage: e.message})
    })), q
}

async function qn(e) {
    try {
        return await (await Qe()).transaction(C).objectStore(C).get(Ze(e))
    } catch (t) {
        if (t instanceof D) E.warn(t.message); else {
            const n = g.create("idb-get", {originalErrorMessage: t == null ? void 0 : t.message});
            E.warn(n.message)
        }
    }
}

async function ke(e, t) {
    try {
        const r = (await Qe()).transaction(C, "readwrite");
        await r.objectStore(C).put(t, Ze(e)), await r.done
    } catch (n) {
        if (n instanceof D) E.warn(n.message); else {
            const r = g.create("idb-set", {originalErrorMessage: n == null ? void 0 : n.message});
            E.warn(r.message)
        }
    }
}

function Ze(e) {
    return `${e.name}!${e.options.appId}`
}

/**
 * @license
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const zn = 1024, Gn = 30 * 24 * 60 * 60 * 1e3;

class Jn {
    constructor(t) {
        this.container = t, this._heartbeatsCache = null;
        const n = this.container.getProvider("app").getImmediate();
        this._storage = new Xn(n), this._heartbeatsCachePromise = this._storage.read().then(r => (this._heartbeatsCache = r, r))
    }

    async triggerHeartbeat() {
        var t, n;
        const o = this.container.getProvider("platform-logger").getImmediate().getPlatformInfoString(), i = Oe();
        if (!(((t = this._heartbeatsCache) === null || t === void 0 ? void 0 : t.heartbeats) == null && (this._heartbeatsCache = await this._heartbeatsCachePromise, ((n = this._heartbeatsCache) === null || n === void 0 ? void 0 : n.heartbeats) == null)) && !(this._heartbeatsCache.lastSentHeartbeatDate === i || this._heartbeatsCache.heartbeats.some(s => s.date === i))) return this._heartbeatsCache.heartbeats.push({
            date: i,
            agent: o
        }), this._heartbeatsCache.heartbeats = this._heartbeatsCache.heartbeats.filter(s => {
            const a = new Date(s.date).valueOf();
            return Date.now() - a <= Gn
        }), this._storage.overwrite(this._heartbeatsCache)
    }

    async getHeartbeatsHeader() {
        var t;
        if (this._heartbeatsCache === null && await this._heartbeatsCachePromise, ((t = this._heartbeatsCache) === null || t === void 0 ? void 0 : t.heartbeats) == null || this._heartbeatsCache.heartbeats.length === 0) return "";
        const n = Oe(), {heartbeatsToSend: r, unsentEntries: o} = Yn(this._heartbeatsCache.heartbeats),
            i = We(JSON.stringify({version: 2, heartbeats: r}));
        return this._heartbeatsCache.lastSentHeartbeatDate = n, o.length > 0 ? (this._heartbeatsCache.heartbeats = o, await this._storage.overwrite(this._heartbeatsCache)) : (this._heartbeatsCache.heartbeats = [], this._storage.overwrite(this._heartbeatsCache)), i
    }
}

function Oe() {
    return new Date().toISOString().substring(0, 10)
}

function Yn(e, t = zn) {
    const n = [];
    let r = e.slice();
    for (const o of e) {
        const i = n.find(s => s.agent === o.agent);
        if (i) {
            if (i.dates.push(o.date), Ne(n) > t) {
                i.dates.pop();
                break
            }
        } else if (n.push({agent: o.agent, dates: [o.date]}), Ne(n) > t) {
            n.pop();
            break
        }
        r = r.slice(1)
    }
    return {heartbeatsToSend: n, unsentEntries: r}
}

class Xn {
    constructor(t) {
        this.app = t, this._canUseIndexedDBPromise = this.runIndexedDBEnvironmentCheck()
    }

    async runIndexedDBEnvironmentCheck() {
        return ze() ? Ge().then(() => !0).catch(() => !1) : !1
    }

    async read() {
        if (await this._canUseIndexedDBPromise) {
            const n = await qn(this.app);
            return n != null && n.heartbeats ? n : {heartbeats: []}
        } else return {heartbeats: []}
    }

    async overwrite(t) {
        var n;
        if (await this._canUseIndexedDBPromise) {
            const o = await this.read();
            return ke(this.app, {
                lastSentHeartbeatDate: (n = t.lastSentHeartbeatDate) !== null && n !== void 0 ? n : o.lastSentHeartbeatDate,
                heartbeats: t.heartbeats
            })
        } else return
    }

    async add(t) {
        var n;
        if (await this._canUseIndexedDBPromise) {
            const o = await this.read();
            return ke(this.app, {
                lastSentHeartbeatDate: (n = t.lastSentHeartbeatDate) !== null && n !== void 0 ? n : o.lastSentHeartbeatDate,
                heartbeats: [...o.heartbeats, ...t.heartbeats]
            })
        } else return
    }
}

function Ne(e) {
    return We(JSON.stringify({version: 2, heartbeats: e})).length
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function Qn(e) {
    v(new w("platform-logger", t => new hn(t), "PRIVATE")), v(new w("heartbeat", t => new Jn(t), "PRIVATE")), m(oe, Ce, e), m(oe, Ce, "esm2017"), m("fire-js", "")
}

Qn("");
var Zn = "firebase", er = "10.7.0";
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */m(Zn, er, "app");
const tr = (e, t) => t.some(n => e instanceof n);
let Be, Me;

function nr() {
    return Be || (Be = [IDBDatabase, IDBObjectStore, IDBIndex, IDBCursor, IDBTransaction])
}

function rr() {
    return Me || (Me = [IDBCursor.prototype.advance, IDBCursor.prototype.continue, IDBCursor.prototype.continuePrimaryKey])
}

const et = new WeakMap, ae = new WeakMap, tt = new WeakMap, z = new WeakMap, he = new WeakMap;

function or(e) {
    const t = new Promise((n, r) => {
        const o = () => {
            e.removeEventListener("success", i), e.removeEventListener("error", s)
        }, i = () => {
            n(b(e.result)), o()
        }, s = () => {
            r(e.error), o()
        };
        e.addEventListener("success", i), e.addEventListener("error", s)
    });
    return t.then(n => {
        n instanceof IDBCursor && et.set(n, e)
    }).catch(() => {
    }), he.set(t, e), t
}

function ir(e) {
    if (ae.has(e)) return;
    const t = new Promise((n, r) => {
        const o = () => {
            e.removeEventListener("complete", i), e.removeEventListener("error", s), e.removeEventListener("abort", s)
        }, i = () => {
            n(), o()
        }, s = () => {
            r(e.error || new DOMException("AbortError", "AbortError")), o()
        };
        e.addEventListener("complete", i), e.addEventListener("error", s), e.addEventListener("abort", s)
    });
    ae.set(e, t)
}

let ce = {
    get(e, t, n) {
        if (e instanceof IDBTransaction) {
            if (t === "done") return ae.get(e);
            if (t === "objectStoreNames") return e.objectStoreNames || tt.get(e);
            if (t === "store") return n.objectStoreNames[1] ? void 0 : n.objectStore(n.objectStoreNames[0])
        }
        return b(e[t])
    }, set(e, t, n) {
        return e[t] = n, !0
    }, has(e, t) {
        return e instanceof IDBTransaction && (t === "done" || t === "store") ? !0 : t in e
    }
};

function sr(e) {
    ce = e(ce)
}

function ar(e) {
    return e === IDBDatabase.prototype.transaction && !("objectStoreNames" in IDBTransaction.prototype) ? function (t, ...n) {
        const r = e.call(G(this), t, ...n);
        return tt.set(r, t.sort ? t.sort() : [t]), b(r)
    } : rr().includes(e) ? function (...t) {
        return e.apply(G(this), t), b(et.get(this))
    } : function (...t) {
        return b(e.apply(G(this), t))
    }
}

function cr(e) {
    return typeof e == "function" ? ar(e) : (e instanceof IDBTransaction && ir(e), tr(e, nr()) ? new Proxy(e, ce) : e)
}

function b(e) {
    if (e instanceof IDBRequest) return or(e);
    if (z.has(e)) return z.get(e);
    const t = cr(e);
    return t !== e && (z.set(e, t), he.set(t, e)), t
}

const G = e => he.get(e);

function ur(e, t, {blocked: n, upgrade: r, blocking: o, terminated: i} = {}) {
    const s = indexedDB.open(e, t), a = b(s);
    return r && s.addEventListener("upgradeneeded", c => {
        r(b(s.result), c.oldVersion, c.newVersion, b(s.transaction))
    }), n && s.addEventListener("blocked", () => n()), a.then(c => {
        i && c.addEventListener("close", () => i()), o && c.addEventListener("versionchange", () => o())
    }).catch(() => {
    }), a
}

const dr = ["get", "getKey", "getAll", "getAllKeys", "count"], lr = ["put", "add", "delete", "clear"], J = new Map;

function Pe(e, t) {
    if (!(e instanceof IDBDatabase && !(t in e) && typeof t == "string")) return;
    if (J.get(t)) return J.get(t);
    const n = t.replace(/FromIndex$/, ""), r = t !== n, o = lr.includes(n);
    if (!(n in (r ? IDBIndex : IDBObjectStore).prototype) || !(o || dr.includes(n))) return;
    const i = async function (s, ...a) {
        const c = this.transaction(s, o ? "readwrite" : "readonly");
        let u = c.store;
        return r && (u = u.index(a.shift())), (await Promise.all([u[n](...a), o && c.done]))[0]
    };
    return J.set(t, i), i
}

sr(e => ({...e, get: (t, n, r) => Pe(t, n) || e.get(t, n, r), has: (t, n) => !!Pe(t, n) || e.has(t, n)}));
const nt = "@firebase/installations", pe = "0.6.4";
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const rt = 1e4, ot = `w:${pe}`, it = "FIS_v2", fr = "https://firebaseinstallations.googleapis.com/v1",
    hr = 60 * 60 * 1e3, pr = "installations", gr = "Installations";
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const mr = {
    "missing-app-config-values": 'Missing App configuration value: "{$valueName}"',
    "not-registered": "Firebase Installation is not registered.",
    "installation-not-found": "Firebase Installation not found.",
    "request-failed": '{$requestName} request failed with error "{$serverCode} {$serverStatus}: {$serverMessage}"',
    "app-offline": "Could not process request. Application offline.",
    "delete-pending-registration": "Can't delete installation while there is a pending registration request."
}, _ = new F(pr, gr, mr);

function st(e) {
    return e instanceof D && e.code.includes("request-failed")
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function at({projectId: e}) {
    return `${fr}/projects/${e}/installations`
}

function ct(e) {
    return {token: e.token, requestStatus: 2, expiresIn: wr(e.expiresIn), creationTime: Date.now()}
}

async function ut(e, t) {
    const r = (await t.json()).error;
    return _.create("request-failed", {
        requestName: e,
        serverCode: r.code,
        serverMessage: r.message,
        serverStatus: r.status
    })
}

function dt({apiKey: e}) {
    return new Headers({"Content-Type": "application/json", Accept: "application/json", "x-goog-api-key": e})
}

function br(e, {refreshToken: t}) {
    const n = dt(e);
    return n.append("Authorization", yr(t)), n
}

async function lt(e) {
    const t = await e();
    return t.status >= 500 && t.status < 600 ? e() : t
}

function wr(e) {
    return Number(e.replace("s", "000"))
}

function yr(e) {
    return `${it} ${e}`
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */async function Ir({appConfig: e, heartbeatServiceProvider: t}, {fid: n}) {
    const r = at(e), o = dt(e), i = t.getImmediate({optional: !0});
    if (i) {
        const u = await i.getHeartbeatsHeader();
        u && o.append("x-firebase-client", u)
    }
    const s = {fid: n, authVersion: it, appId: e.appId, sdkVersion: ot},
        a = {method: "POST", headers: o, body: JSON.stringify(s)}, c = await lt(() => fetch(r, a));
    if (c.ok) {
        const u = await c.json();
        return {fid: u.fid || n, registrationStatus: 2, refreshToken: u.refreshToken, authToken: ct(u.authToken)}
    } else throw await ut("Create Installation", c)
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function ft(e) {
    return new Promise(t => {
        setTimeout(t, e)
    })
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function Er(e) {
    return btoa(String.fromCharCode(...e)).replace(/\+/g, "-").replace(/\//g, "_")
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const vr = /^[cdef][\w-]{21}$/, ue = "";

function _r() {
    try {
        const e = new Uint8Array(17);
        (self.crypto || self.msCrypto).getRandomValues(e), e[0] = 112 + e[0] % 16;
        const n = Sr(e);
        return vr.test(n) ? n : ue
    } catch {
        return ue
    }
}

function Sr(e) {
    return Er(e).substr(0, 22)
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function j(e) {
    return `${e.appName}!${e.appId}`
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const ht = new Map;

function pt(e, t) {
    const n = j(e);
    gt(n, t), Tr(n, t)
}

function gt(e, t) {
    const n = ht.get(e);
    if (n) for (const r of n) r(t)
}

function Tr(e, t) {
    const n = Dr();
    n && n.postMessage({key: e, fid: t}), Ar()
}

let I = null;

function Dr() {
    return !I && "BroadcastChannel" in self && (I = new BroadcastChannel("[Firebase] FID Change"), I.onmessage = e => {
        gt(e.data.key, e.data.fid)
    }), I
}

function Ar() {
    ht.size === 0 && I && (I.close(), I = null)
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const Cr = "firebase-installations-database", kr = 1, S = "firebase-installations-store";
let Y = null;

function ge() {
    return Y || (Y = ur(Cr, kr, {
        upgrade: (e, t) => {
            switch (t) {
                case 0:
                    e.createObjectStore(S)
            }
        }
    })), Y
}

async function M(e, t) {
    const n = j(e), o = (await ge()).transaction(S, "readwrite"), i = o.objectStore(S), s = await i.get(n);
    return await i.put(t, n), await o.done, (!s || s.fid !== t.fid) && pt(e, t.fid), t
}

async function mt(e) {
    const t = j(e), r = (await ge()).transaction(S, "readwrite");
    await r.objectStore(S).delete(t), await r.done
}

async function H(e, t) {
    const n = j(e), o = (await ge()).transaction(S, "readwrite"), i = o.objectStore(S), s = await i.get(n), a = t(s);
    return a === void 0 ? await i.delete(n) : await i.put(a, n), await o.done, a && (!s || s.fid !== a.fid) && pt(e, a.fid), a
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */async function me(e) {
    let t;
    const n = await H(e.appConfig, r => {
        const o = Or(r), i = Nr(e, o);
        return t = i.registrationPromise, i.installationEntry
    });
    return n.fid === ue ? {installationEntry: await t} : {installationEntry: n, registrationPromise: t}
}

function Or(e) {
    const t = e || {fid: _r(), registrationStatus: 0};
    return bt(t)
}

function Nr(e, t) {
    if (t.registrationStatus === 0) {
        if (!navigator.onLine) {
            const o = Promise.reject(_.create("app-offline"));
            return {installationEntry: t, registrationPromise: o}
        }
        const n = {fid: t.fid, registrationStatus: 1, registrationTime: Date.now()}, r = Br(e, n);
        return {installationEntry: n, registrationPromise: r}
    } else return t.registrationStatus === 1 ? {
        installationEntry: t,
        registrationPromise: Mr(e)
    } : {installationEntry: t}
}

async function Br(e, t) {
    try {
        const n = await Ir(e, t);
        return M(e.appConfig, n)
    } catch (n) {
        throw st(n) && n.customData.serverCode === 409 ? await mt(e.appConfig) : await M(e.appConfig, {
            fid: t.fid,
            registrationStatus: 0
        }), n
    }
}

async function Mr(e) {
    let t = await Re(e.appConfig);
    for (; t.registrationStatus === 1;) await ft(100), t = await Re(e.appConfig);
    if (t.registrationStatus === 0) {
        const {installationEntry: n, registrationPromise: r} = await me(e);
        return r || n
    }
    return t
}

function Re(e) {
    return H(e, t => {
        if (!t) throw _.create("installation-not-found");
        return bt(t)
    })
}

function bt(e) {
    return Pr(e) ? {fid: e.fid, registrationStatus: 0} : e
}

function Pr(e) {
    return e.registrationStatus === 1 && e.registrationTime + rt < Date.now()
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */async function Rr({appConfig: e, heartbeatServiceProvider: t}, n) {
    const r = $r(e, n), o = br(e, n), i = t.getImmediate({optional: !0});
    if (i) {
        const u = await i.getHeartbeatsHeader();
        u && o.append("x-firebase-client", u)
    }
    const s = {installation: {sdkVersion: ot, appId: e.appId}},
        a = {method: "POST", headers: o, body: JSON.stringify(s)}, c = await lt(() => fetch(r, a));
    if (c.ok) {
        const u = await c.json();
        return ct(u)
    } else throw await ut("Generate Auth Token", c)
}

function $r(e, {fid: t}) {
    return `${at(e)}/${t}/authTokens:generate`
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */async function be(e, t = !1) {
    let n;
    const r = await H(e.appConfig, i => {
        if (!wt(i)) throw _.create("not-registered");
        const s = i.authToken;
        if (!t && xr(s)) return i;
        if (s.requestStatus === 1) return n = Lr(e, t), i;
        {
            if (!navigator.onLine) throw _.create("app-offline");
            const a = Hr(i);
            return n = Fr(e, a), a
        }
    });
    return n ? await n : r.authToken
}

async function Lr(e, t) {
    let n = await $e(e.appConfig);
    for (; n.authToken.requestStatus === 1;) await ft(100), n = await $e(e.appConfig);
    const r = n.authToken;
    return r.requestStatus === 0 ? be(e, t) : r
}

function $e(e) {
    return H(e, t => {
        if (!wt(t)) throw _.create("not-registered");
        const n = t.authToken;
        return Vr(n) ? Object.assign(Object.assign({}, t), {authToken: {requestStatus: 0}}) : t
    })
}

async function Fr(e, t) {
    try {
        const n = await Rr(e, t), r = Object.assign(Object.assign({}, t), {authToken: n});
        return await M(e.appConfig, r), n
    } catch (n) {
        if (st(n) && (n.customData.serverCode === 401 || n.customData.serverCode === 404)) await mt(e.appConfig); else {
            const r = Object.assign(Object.assign({}, t), {authToken: {requestStatus: 0}});
            await M(e.appConfig, r)
        }
        throw n
    }
}

function wt(e) {
    return e !== void 0 && e.registrationStatus === 2
}

function xr(e) {
    return e.requestStatus === 2 && !jr(e)
}

function jr(e) {
    const t = Date.now();
    return t < e.creationTime || e.creationTime + e.expiresIn < t + hr
}

function Hr(e) {
    const t = {requestStatus: 1, requestTime: Date.now()};
    return Object.assign(Object.assign({}, e), {authToken: t})
}

function Vr(e) {
    return e.requestStatus === 1 && e.requestTime + rt < Date.now()
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */async function Ur(e) {
    const t = e, {installationEntry: n, registrationPromise: r} = await me(t);
    return r ? r.catch(console.error) : be(t).catch(console.error), n.fid
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */async function Kr(e, t = !1) {
    const n = e;
    return await Wr(n), (await be(n, t)).token
}

async function Wr(e) {
    const {registrationPromise: t} = await me(e);
    t && await t
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function qr(e) {
    if (!e || !e.options) throw X("App Configuration");
    if (!e.name) throw X("App Name");
    const t = ["projectId", "apiKey", "appId"];
    for (const n of t) if (!e.options[n]) throw X(n);
    return {appName: e.name, projectId: e.options.projectId, apiKey: e.options.apiKey, appId: e.options.appId}
}

function X(e) {
    return _.create("missing-app-config-values", {valueName: e})
}

/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const yt = "installations", zr = "installations-internal", Gr = e => {
    const t = e.getProvider("app").getImmediate(), n = qr(t), r = fe(t, "heartbeat");
    return {app: t, appConfig: n, heartbeatServiceProvider: r, _delete: () => Promise.resolve()}
}, Jr = e => {
    const t = e.getProvider("app").getImmediate(), n = fe(t, yt).getImmediate();
    return {getId: () => Ur(n), getToken: o => Kr(n, o)}
};

function Yr() {
    v(new w(yt, Gr, "PUBLIC")), v(new w(zr, Jr, "PRIVATE"))
}

Yr();
m(nt, pe);
m(nt, pe, "esm2017");
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const Xr = "/firebase-messaging-sw.js", Qr = "/firebase-cloud-messaging-push-scope",
    It = "BDOU99-h67HcA6JeFXHbSNMu7e2yNNu3RzoMj8TM4W88jITfq7ZmPvIM1Iv-4_l2LxQcYwhqby2xGpWwzjfAnG4",
    Zr = "https://fcmregistrations.googleapis.com/v1", Et = "google.c.a.c_id", eo = "google.c.a.c_l",
    to = "google.c.a.ts", no = "google.c.a.e";
var Le;
(function (e) {
    e[e.DATA_MESSAGE = 1] = "DATA_MESSAGE", e[e.DISPLAY_NOTIFICATION = 3] = "DISPLAY_NOTIFICATION"
})(Le || (Le = {}));
/**
 * @license
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */var k;
(function (e) {
    e.PUSH_RECEIVED = "push-received", e.NOTIFICATION_CLICKED = "notification-clicked"
})(k || (k = {}));

/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function h(e) {
    const t = new Uint8Array(e);
    return btoa(String.fromCharCode(...t)).replace(/=/g, "").replace(/\+/g, "-").replace(/\//g, "_")
}

function ro(e) {
    const t = "=".repeat((4 - e.length % 4) % 4), n = (e + t).replace(/\-/g, "+").replace(/_/g, "/"), r = atob(n),
        o = new Uint8Array(r.length);
    for (let i = 0; i < r.length; ++i) o[i] = r.charCodeAt(i);
    return o
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const Q = "fcm_token_details_db", oo = 5, Fe = "fcm_token_object_Store";

async function io(e) {
    if ("databases" in indexedDB && !(await indexedDB.databases()).map(i => i.name).includes(Q)) return null;
    let t = null;
    return (await le(Q, oo, {
        upgrade: async (r, o, i, s) => {
            var a;
            if (o < 2 || !r.objectStoreNames.contains(Fe)) return;
            const c = s.objectStore(Fe), u = await c.index("fcmSenderId").get(e);
            if (await c.clear(), !!u) {
                if (o === 2) {
                    const d = u;
                    if (!d.auth || !d.p256dh || !d.endpoint) return;
                    t = {
                        token: d.fcmToken,
                        createTime: (a = d.createTime) !== null && a !== void 0 ? a : Date.now(),
                        subscriptionOptions: {
                            auth: d.auth,
                            p256dh: d.p256dh,
                            endpoint: d.endpoint,
                            swScope: d.swScope,
                            vapidKey: typeof d.vapidKey == "string" ? d.vapidKey : h(d.vapidKey)
                        }
                    }
                } else if (o === 3) {
                    const d = u;
                    t = {
                        token: d.fcmToken,
                        createTime: d.createTime,
                        subscriptionOptions: {
                            auth: h(d.auth),
                            p256dh: h(d.p256dh),
                            endpoint: d.endpoint,
                            swScope: d.swScope,
                            vapidKey: h(d.vapidKey)
                        }
                    }
                } else if (o === 4) {
                    const d = u;
                    t = {
                        token: d.fcmToken,
                        createTime: d.createTime,
                        subscriptionOptions: {
                            auth: h(d.auth),
                            p256dh: h(d.p256dh),
                            endpoint: d.endpoint,
                            swScope: d.swScope,
                            vapidKey: h(d.vapidKey)
                        }
                    }
                }
            }
        }
    })).close(), await K(Q), await K("fcm_vapid_details_db"), await K("undefined"), so(t) ? t : null
}

function so(e) {
    if (!e || !e.subscriptionOptions) return !1;
    const {subscriptionOptions: t} = e;
    return typeof e.createTime == "number" && e.createTime > 0 && typeof e.token == "string" && e.token.length > 0 && typeof t.auth == "string" && t.auth.length > 0 && typeof t.p256dh == "string" && t.p256dh.length > 0 && typeof t.endpoint == "string" && t.endpoint.length > 0 && typeof t.swScope == "string" && t.swScope.length > 0 && typeof t.vapidKey == "string" && t.vapidKey.length > 0
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const ao = "firebase-messaging-database", co = 1, T = "firebase-messaging-store";
let Z = null;

function we() {
    return Z || (Z = le(ao, co, {
        upgrade: (e, t) => {
            switch (t) {
                case 0:
                    e.createObjectStore(T)
            }
        }
    })), Z
}

async function vt(e) {
    const t = Ie(e), r = await (await we()).transaction(T).objectStore(T).get(t);
    if (r) return r;
    {
        const o = await io(e.appConfig.senderId);
        if (o) return await ye(e, o), o
    }
}

async function ye(e, t) {
    const n = Ie(e), o = (await we()).transaction(T, "readwrite");
    return await o.objectStore(T).put(t, n), await o.done, t
}

async function uo(e) {
    const t = Ie(e), r = (await we()).transaction(T, "readwrite");
    await r.objectStore(T).delete(t), await r.done
}

function Ie({appConfig: e}) {
    return e.appId
}

/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const lo = {
    "missing-app-config-values": 'Missing App configuration value: "{$valueName}"',
    "only-available-in-window": "This method is available in a Window context.",
    "only-available-in-sw": "This method is available in a service worker context.",
    "permission-default": "The notification permission was not granted and dismissed instead.",
    "permission-blocked": "The notification permission was not granted and blocked instead.",
    "unsupported-browser": "This browser doesn't support the API's required to use the Firebase SDK.",
    "indexed-db-unsupported": "This browser doesn't support indexedDb.open() (ex. Safari iFrame, Firefox Private Browsing, etc)",
    "failed-service-worker-registration": "We are unable to register the default service worker. {$browserErrorMessage}",
    "token-subscribe-failed": "A problem occurred while subscribing the user to FCM: {$errorInfo}",
    "token-subscribe-no-token": "FCM returned no token when subscribing the user to push.",
    "token-unsubscribe-failed": "A problem occurred while unsubscribing the user from FCM: {$errorInfo}",
    "token-update-failed": "A problem occurred while updating the user from FCM: {$errorInfo}",
    "token-update-no-token": "FCM returned no token when updating the user to push.",
    "use-sw-after-get-token": "The useServiceWorker() method may only be called once and must be called before calling getToken() to ensure your service worker is used.",
    "invalid-sw-registration": "The input to useServiceWorker() must be a ServiceWorkerRegistration.",
    "invalid-bg-handler": "The input to setBackgroundMessageHandler() must be a function.",
    "invalid-vapid-key": "The public VAPID key must be a string.",
    "use-vapid-key-after-get-token": "The usePublicVapidKey() method may only be called once and must be called before calling getToken() to ensure your VAPID key is used."
}, f = new F("messaging", "Messaging", lo);

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */async function fo(e, t) {
    const n = await ve(e), r = St(t), o = {method: "POST", headers: n, body: JSON.stringify(r)};
    let i;
    try {
        i = await (await fetch(Ee(e.appConfig), o)).json()
    } catch (s) {
        throw f.create("token-subscribe-failed", {errorInfo: s == null ? void 0 : s.toString()})
    }
    if (i.error) {
        const s = i.error.message;
        throw f.create("token-subscribe-failed", {errorInfo: s})
    }
    if (!i.token) throw f.create("token-subscribe-no-token");
    return i.token
}

async function ho(e, t) {
    const n = await ve(e), r = St(t.subscriptionOptions), o = {method: "PATCH", headers: n, body: JSON.stringify(r)};
    let i;
    try {
        i = await (await fetch(`${Ee(e.appConfig)}/${t.token}`, o)).json()
    } catch (s) {
        throw f.create("token-update-failed", {errorInfo: s == null ? void 0 : s.toString()})
    }
    if (i.error) {
        const s = i.error.message;
        throw f.create("token-update-failed", {errorInfo: s})
    }
    if (!i.token) throw f.create("token-update-no-token");
    return i.token
}

async function _t(e, t) {
    const r = {method: "DELETE", headers: await ve(e)};
    try {
        const i = await (await fetch(`${Ee(e.appConfig)}/${t}`, r)).json();
        if (i.error) {
            const s = i.error.message;
            throw f.create("token-unsubscribe-failed", {errorInfo: s})
        }
    } catch (o) {
        throw f.create("token-unsubscribe-failed", {errorInfo: o == null ? void 0 : o.toString()})
    }
}

function Ee({projectId: e}) {
    return `${Zr}/projects/${e}/registrations`
}

async function ve({appConfig: e, installations: t}) {
    const n = await t.getToken();
    return new Headers({
        "Content-Type": "application/json",
        Accept: "application/json",
        "x-goog-api-key": e.apiKey,
        "x-goog-firebase-installations-auth": `FIS ${n}`
    })
}

function St({p256dh: e, auth: t, endpoint: n, vapidKey: r}) {
    const o = {web: {endpoint: n, auth: t, p256dh: e}};
    return r !== It && (o.web.applicationPubKey = r), o
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const po = 7 * 24 * 60 * 60 * 1e3;

async function go(e) {
    const t = await bo(e.swRegistration, e.vapidKey), n = {
        vapidKey: e.vapidKey,
        swScope: e.swRegistration.scope,
        endpoint: t.endpoint,
        auth: h(t.getKey("auth")),
        p256dh: h(t.getKey("p256dh"))
    }, r = await vt(e.firebaseDependencies);
    if (r) {
        if (wo(r.subscriptionOptions, n)) return Date.now() >= r.createTime + po ? mo(e, {
            token: r.token,
            createTime: Date.now(),
            subscriptionOptions: n
        }) : r.token;
        try {
            await _t(e.firebaseDependencies, r.token)
        } catch (o) {
            console.warn(o)
        }
        return xe(e.firebaseDependencies, n)
    } else return xe(e.firebaseDependencies, n)
}

async function Tt(e) {
    const t = await vt(e.firebaseDependencies);
    t && (await _t(e.firebaseDependencies, t.token), await uo(e.firebaseDependencies));
    const n = await e.swRegistration.pushManager.getSubscription();
    return n ? n.unsubscribe() : !0
}

async function mo(e, t) {
    try {
        const n = await ho(e.firebaseDependencies, t),
            r = Object.assign(Object.assign({}, t), {token: n, createTime: Date.now()});
        return await ye(e.firebaseDependencies, r), n
    } catch (n) {
        throw await Tt(e), n
    }
}

async function xe(e, t) {
    const r = {token: await fo(e, t), createTime: Date.now(), subscriptionOptions: t};
    return await ye(e, r), r.token
}

async function bo(e, t) {
    const n = await e.pushManager.getSubscription();
    return n || e.pushManager.subscribe({userVisibleOnly: !0, applicationServerKey: ro(t)})
}

function wo(e, t) {
    const n = t.vapidKey === e.vapidKey, r = t.endpoint === e.endpoint, o = t.auth === e.auth,
        i = t.p256dh === e.p256dh;
    return n && r && o && i
}

/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function je(e) {
    const t = {from: e.from, collapseKey: e.collapse_key, messageId: e.fcmMessageId};
    return yo(t, e), Io(t, e), Eo(t, e), t
}

function yo(e, t) {
    if (!t.notification) return;
    e.notification = {};
    const n = t.notification.title;
    n && (e.notification.title = n);
    const r = t.notification.body;
    r && (e.notification.body = r);
    const o = t.notification.image;
    o && (e.notification.image = o);
    const i = t.notification.icon;
    i && (e.notification.icon = i)
}

function Io(e, t) {
    t.data && (e.data = t.data)
}

function Eo(e, t) {
    var n, r, o, i, s;
    if (!t.fcmOptions && !(!((n = t.notification) === null || n === void 0) && n.click_action)) return;
    e.fcmOptions = {};
    const a = (o = (r = t.fcmOptions) === null || r === void 0 ? void 0 : r.link) !== null && o !== void 0 ? o : (i = t.notification) === null || i === void 0 ? void 0 : i.click_action;
    a && (e.fcmOptions.link = a);
    const c = (s = t.fcmOptions) === null || s === void 0 ? void 0 : s.analytics_label;
    c && (e.fcmOptions.analyticsLabel = c)
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function vo(e) {
    return typeof e == "object" && !!e && Et in e
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */Dt("hts/frbslgigp.ogepscmv/ieo/eaylg", "tp:/ieaeogn-agolai.o/1frlglgc/o");
Dt("AzSCbw63g1R0nCw85jG8", "Iaya3yLKwmgvh7cF0q4");

function Dt(e, t) {
    const n = [];
    for (let r = 0; r < e.length; r++) n.push(e.charAt(r)), r < t.length && n.push(t.charAt(r));
    return n.join("")
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function _o(e) {
    if (!e || !e.options) throw ee("App Configuration Object");
    if (!e.name) throw ee("App Name");
    const t = ["projectId", "apiKey", "appId", "messagingSenderId"], {options: n} = e;
    for (const r of t) if (!n[r]) throw ee(r);
    return {appName: e.name, projectId: n.projectId, apiKey: n.apiKey, appId: n.appId, senderId: n.messagingSenderId}
}

function ee(e) {
    return f.create("missing-app-config-values", {valueName: e})
}

/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class So {
    constructor(t, n, r) {
        this.deliveryMetricsExportedToBigQueryEnabled = !1, this.onBackgroundMessageHandler = null, this.onMessageHandler = null, this.logEvents = [], this.isLogServiceStarted = !1;
        const o = _o(t);
        this.firebaseDependencies = {app: t, appConfig: o, installations: n, analyticsProvider: r}
    }

    _delete() {
        return Promise.resolve()
    }
}

/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */async function At(e) {
    try {
        e.swRegistration = await navigator.serviceWorker.register(Xr, {scope: Qr}), e.swRegistration.update().catch(() => {
        })
    } catch (t) {
        throw f.create("failed-service-worker-registration", {browserErrorMessage: t == null ? void 0 : t.message})
    }
}

/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */async function To(e, t) {
    if (!t && !e.swRegistration && await At(e), !(!t && e.swRegistration)) {
        if (!(t instanceof ServiceWorkerRegistration)) throw f.create("invalid-sw-registration");
        e.swRegistration = t
    }
}

/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */async function Do(e, t) {
    t ? e.vapidKey = t : e.vapidKey || (e.vapidKey = It)
}

/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */async function Ct(e, t) {
    if (!navigator) throw f.create("only-available-in-window");
    if (Notification.permission === "default" && await Notification.requestPermission(), Notification.permission !== "granted") throw f.create("permission-blocked");
    return await Do(e, t == null ? void 0 : t.vapidKey), await To(e, t == null ? void 0 : t.serviceWorkerRegistration), go(e)
}

/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */async function Ao(e, t, n) {
    const r = Co(t);
    (await e.firebaseDependencies.analyticsProvider.get()).logEvent(r, {
        message_id: n[Et],
        message_name: n[eo],
        message_time: n[to],
        message_device_time: Math.floor(Date.now() / 1e3)
    })
}

function Co(e) {
    switch (e) {
        case k.NOTIFICATION_CLICKED:
            return "notification_open";
        case k.PUSH_RECEIVED:
            return "notification_foreground";
        default:
            throw new Error
    }
}

/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */async function ko(e, t) {
    const n = t.data;
    if (!n.isFirebaseMessaging) return;
    e.onMessageHandler && n.messageType === k.PUSH_RECEIVED && (typeof e.onMessageHandler == "function" ? e.onMessageHandler(je(n)) : e.onMessageHandler.next(je(n)));
    const r = n.data;
    vo(r) && r[no] === "1" && await Ao(e, n.messageType, r)
}

const He = "@firebase/messaging", Ve = "0.12.5";
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const Oo = e => {
    const t = new So(e.getProvider("app").getImmediate(), e.getProvider("installations-internal").getImmediate(), e.getProvider("analytics-internal"));
    return navigator.serviceWorker.addEventListener("message", n => ko(t, n)), t
}, No = e => {
    const t = e.getProvider("messaging").getImmediate();
    return {getToken: r => Ct(t, r)}
};

function Bo() {
    v(new w("messaging", Oo, "PUBLIC")), v(new w("messaging-internal", No, "PRIVATE")), m(He, Ve), m(He, Ve, "esm2017")
}

/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */async function Mo() {
    try {
        await Ge()
    } catch {
        return !1
    }
    return typeof window < "u" && ze() && Ut() && "serviceWorker" in navigator && "PushManager" in window && "Notification" in window && "fetch" in window && ServiceWorkerRegistration.prototype.hasOwnProperty("showNotification") && PushSubscription.prototype.hasOwnProperty("getKey")
}

/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */async function Po(e) {
    if (!navigator) throw f.create("only-available-in-window");
    return e.swRegistration || await At(e), Tt(e)
}

/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function Ro(e, t) {
    if (!navigator) throw f.create("only-available-in-window");
    return e.onMessageHandler = t, () => {
        e.onMessageHandler = null
    }
}

/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function $o(e = Un()) {
    return Mo().then(t => {
        if (!t) throw f.create("unsupported-browser")
    }, t => {
        throw f.create("indexed-db-unsupported")
    }), fe(x(e), "messaging").getImmediate()
}

async function kt(e, t) {
    return e = x(e), Ct(e, t)
}

function Lo(e) {
    return e = x(e), Po(e)
}

function Fo(e, t) {
    return e = x(e), Ro(e, t)
}

Bo();
const xo = {
    apiKey: "API_KEY",
    authDomain: "PROJECT_ID.firebaseapp.com",
    databaseURL: "https://PROJECT_ID.firebaseio.com",
    projectId: "PROJECT_ID",
    storageBucket: "PROJECT_ID.appspot.com",
    messagingSenderId: "SENDER_ID",
    appId: "APP_ID",
    measurementId: "G-MEASUREMENT_ID"
}, jo = "<YOUR_PUBLIC_VAPID_KEY_HERE>";
Xe(xo);
const P = $o(), Ot = "token_div", Nt = "permission_div";
Fo(P, e => {
    console.log("Message received. ", e), Wo(e)
});

function _e() {
    qo(), R("loading..."), kt(P, {vapidKey: jo}).then(e => {
        e ? (Ho(e), zo(e)) : (console.log("No registration token available. Request permission to generate one."), Go(), $(!1))
    }).catch(e => {
        console.log("An error occurred while retrieving token. ", e), R("Error retrieving registration token."), $(!1)
    })
}

function R(e) {
    const t = document.querySelector("#token");
    t.textContent = e
}

function Ho(e) {
    Vo() ? console.log("Token already sent to server so won't send it again unless it changes") : (console.log("Sending token to server...", e), $(!0))
}

function Vo() {
    return window.localStorage.getItem("sentToServer") === "1"
}

function $(e) {
    window.localStorage.setItem("sentToServer", e ? "1" : "0")
}

function L(e, t) {
    const n = document.querySelector("#" + e);
    t ? n.style.display = "block" : n.style.display = "none"
}

function Uo() {
    console.log("Requesting permission..."), Notification.requestPermission().then(e => {
        e === "granted" ? (console.log("Notification permission granted."), _e()) : console.log("Unable to get permission to notify.")
    })
}

function Ko() {
    kt(P).then(e => {
        Lo(P).then(() => {
            console.log("Token deleted.", e), $(!1), _e()
        }).catch(t => {
            console.log("Unable to delete token. ", t)
        })
    }).catch(e => {
        console.log("Error retrieving registration token. ", e), R("Error retrieving registration token.")
    })
}

function Wo(e) {
    const t = document.querySelector("#messages"), n = document.createElement("h5"), r = document.createElement("pre");
    r.style.overflowX = "hidden;", n.textContent = "Received message:", r.textContent = JSON.stringify(e, null, 2), t.appendChild(n), t.appendChild(r)
}

function qo() {
    const e = document.querySelector("#messages");
    for (; e.hasChildNodes();) e.removeChild(e.lastChild)
}

function zo(e) {
    L(Ot, !0), L(Nt, !1), R(e)
}

function Go() {
    L(Ot, !1), L(Nt, !0)
}

document.getElementById("request-permission-button").addEventListener("click", Uo);
document.getElementById("delete-token-button").addEventListener("click", Ko);
_e();
