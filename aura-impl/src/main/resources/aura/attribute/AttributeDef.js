/*
 * Copyright (C) 2013 salesforce.com, inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*jslint sub: true */
/**
 * @namespace Creates a new AttributeDef instance, including the descriptor.
 * @constructor
 * @param {Object} config
 */
function AttributeDef(config){
    this.descriptor = new DefDescriptor(config["name"]);
    this.typeDefDescriptor = config["type"];
    this.defaultValue = config["default"];
    this.required = config["required"] === true;
}

AttributeDef.prototype.auraType = "AttributeDef";

/**
 * Gets the descriptor. Returns a DefDescriptor object that contains the metadata for the attribute.
 * @returns {DefDescriptor} The qualified name for a DefDescriptor object has the format <code>prefix://namespace:name</code>.
 */
AttributeDef.prototype.getDescriptor = function(){
    return this.descriptor;
};

/**
 * Checks whether the attribute definition is required.
 * Returns true by default.
 * @returns {Boolean} True by default.
 */
AttributeDef.prototype.isRequired = function(){
    return this.required === true;
};

/**
 * Gets the type definition. Returns a TypeDef object.
 * @private
 * @returns {TypeDef}
 */
AttributeDef.prototype.getTypeDef = function(){
    return this.typeDef;
};

/**
 * Gets the default value.
 * @returns {Object}
 */
AttributeDef.prototype.getDefault = function(){
    return this.defaultValue;
};

/**
 * Gets the type of the definition descriptor.
 * @private
 * @returns {String}
 */
AttributeDef.prototype.getTypeDefDescriptor = function(){
    return this.typeDefDescriptor;
};

//#include aura.attribute.AttributeDef_export
