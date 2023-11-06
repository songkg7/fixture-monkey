package com.navercorp.fixturemonkey.tests.kotlin;

import java.beans.ConstructorProperties;
import java.util.List;

class JavaExpGetterTestSpecs {
	public static class JavaTypeObject {
		private final String string;
		private final int integer;
		private final List<String> list;
		private final InnerJavaTypeObject obj;

		@ConstructorProperties({"string", "integer", "list", "obj"})
		public JavaTypeObject(String string, int integer, List<String> list, InnerJavaTypeObject obj) {
			this.string = string;
			this.integer = integer;
			this.list = list;
			this.obj = obj;
		}

		public String getString() {
			return string;
		}

		public int getInteger() {
			return integer;
		}

		public List<String> getList() {
			return list;
		}

		public InnerJavaTypeObject getObj() {
			return obj;
		}
	}

	public static class InnerJavaTypeObject {
		private final String string;
		private final List<String> list;

		@ConstructorProperties({"string", "list"})
		public InnerJavaTypeObject(String string, List<String> list) {
			this.string = string;
			this.list = list;
		}

		public String getString() {
			return string;
		}

		public List<String> getList() {
			return list;
		}
	}
}
