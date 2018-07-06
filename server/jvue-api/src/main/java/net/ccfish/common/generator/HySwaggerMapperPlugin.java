package net.ccfish.common.generator;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.util.StringUtility;

import tk.mybatis.mapper.util.StringUtil;

public class HySwaggerMapperPlugin extends MyPgMapperPlugin {
	private boolean implementSerializableInteface;
	private boolean modelFieldEnum;
	private boolean swaggerApiEnabled;
	private boolean columnTypeEnabled;
	private boolean setterMethodChainEnabled;

	public HySwaggerMapperPlugin() {
		this.implementSerializableInteface = false;
		this.modelFieldEnum = false;
		this.swaggerApiEnabled = false;
		this.columnTypeEnabled = false;
		this.setterMethodChainEnabled = false;
	}

	public void setProperties(Properties properties) {
		super.setProperties(properties);
		String sImplementSerializableInteface = this.properties.getProperty("implementSerializableInteface");
		if (StringUtility.stringHasValue(sImplementSerializableInteface)) {
			this.implementSerializableInteface = Boolean.parseBoolean(sImplementSerializableInteface);
		}

		String sModelFieldEnum = this.properties.getProperty("modelFieldEnum");
		if (StringUtility.stringHasValue(sModelFieldEnum)) {
			this.modelFieldEnum = Boolean.parseBoolean(sModelFieldEnum);
		}
		String swaggerApiEnabled = this.properties.getProperty("swaggerApiEnabled");
		if (StringUtility.stringHasValue(swaggerApiEnabled))
			this.swaggerApiEnabled = Boolean.parseBoolean(swaggerApiEnabled);
		
		String columnTypeEnabled = this.properties.getProperty("columnTypeEnabled");
		if (StringUtility.stringHasValue(columnTypeEnabled))
			this.columnTypeEnabled = Boolean.parseBoolean(columnTypeEnabled);
	}

	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
		impSerializableInterface(topLevelClass, introspectedTable);
		addFieldEnum(topLevelClass, introspectedTable);
		addClassComment(topLevelClass);
		swaggerApiAnnotation(topLevelClass, introspectedTable);
		columnTypeAnnotation(topLevelClass, introspectedTable);
		return true;
	}

	public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		super.modelPrimaryKeyClassGenerated(topLevelClass, introspectedTable);
		impSerializableInterface(topLevelClass, introspectedTable);
		addFieldEnum(topLevelClass, introspectedTable);
		addClassComment(topLevelClass);
		swaggerApiAnnotation(topLevelClass, introspectedTable);
		columnTypeAnnotation(topLevelClass, introspectedTable);
		return true;
	}

	public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		super.modelRecordWithBLOBsClassGenerated(topLevelClass, introspectedTable);
		impSerializableInterface(topLevelClass, introspectedTable);
		addFieldEnum(topLevelClass, introspectedTable);
		addClassComment(topLevelClass);
		swaggerApiAnnotation(topLevelClass, introspectedTable);
		columnTypeAnnotation(topLevelClass, introspectedTable);
		return false;
	}
	
	@Override
	public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		if (setterMethodChainEnabled) {
			method.setReturnType(new FullyQualifiedJavaType(topLevelClass.getType().getShortName()));
			method.addBodyLine("return this;");
		}
		return true;
	}

	private void impSerializableInterface(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		if (this.implementSerializableInteface) {
			String serializable = "java.io.Serializable";
			topLevelClass.addImportedType(serializable);
			FullyQualifiedJavaType superInterface = new FullyQualifiedJavaType(serializable);
			topLevelClass.addSuperInterface(superInterface);

			Field serialVersionUID = new Field("serialVersionUID", new FullyQualifiedJavaType("long"));
			serialVersionUID.setVisibility(JavaVisibility.PRIVATE);
			serialVersionUID.setStatic(true);
			serialVersionUID.setFinal(true);

			serialVersionUID.setInitializationString("1L");
			topLevelClass.addField(serialVersionUID);
		}
	}

	private void swaggerApiAnnotation(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		List<String> annotations = topLevelClass.getAnnotations();
		Iterator i$ = null;
		if (this.swaggerApiEnabled) {
			String apiModel = "io.swagger.annotations.ApiModel";
			String apiModelProperty = "io.swagger.annotations.ApiModelProperty";
			topLevelClass.addImportedType(apiModel);
			topLevelClass.addImportedType(apiModelProperty);
			String remarks = introspectedTable.getRemarks();
			if (StringUtil.isEmpty(remarks)) {
				remarks = "";
			}else{
				remarks = remarks.replaceAll("\n", " ");
			}
			if (remarks.endsWith("表")) {
				remarks = remarks.substring(0, remarks.length() - 1) + "对象";
			}
			remarks = topLevelClass.getType().getShortName() + "（" + remarks + "）";
			topLevelClass.addAnnotation("@ApiModel(\"" + remarks + "\")");
			List<Field> fields = topLevelClass.getFields();
			for (i$ = fields.iterator(); i$.hasNext();) {
				Field field = (Field) i$.next();
				List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
				for (IntrospectedColumn introspectedColumn : allColumns) {
					if (field.getName().equals(introspectedColumn.getJavaProperty())) {
						String remark = introspectedColumn.getRemarks();
						if(StringUtil.isEmpty(remark)){
							remark = "";
						}else{
							remark = remark.replaceAll("\n", " ");
						}
						field.addAnnotation("@ApiModelProperty(value =\"" + remark + "\",required = false)");
	                    if (!introspectedTable.getPrimaryKeyColumns().contains(introspectedColumn)) {
	                        if ("version".equalsIgnoreCase(introspectedColumn.getJavaProperty())) {
	                            // 处理乐观锁 2018-04-25
	                            topLevelClass.addImportedType(new FullyQualifiedJavaType("tk.mybatis.mapper.annotation.Version"));
	                            field.addAnnotation("@Version");
	                        }
	                    }
					}
		        }
			}
		}
	}
	
	private void columnTypeAnnotation(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		if (this.columnTypeEnabled) {
			String columnType = "tk.mybatis.mapper.annotation.ColumnType";
			String jdbcType = "org.apache.ibatis.type.JdbcType";
			topLevelClass.addImportedType(columnType);
			topLevelClass.addImportedType(jdbcType);
			List<Field> fields = topLevelClass.getFields();
			for (Field field : fields) {
				List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
				for (IntrospectedColumn introspectedColumn : allColumns)
					if (field.getName().equals(introspectedColumn.getJavaProperty())) {
						String jdbcTypeName = introspectedColumn.getJdbcTypeName();
						if(StringUtil.isEmpty(jdbcTypeName)){
							continue;
						}else{
							jdbcTypeName="JdbcType."+jdbcTypeName;
						}
						field.addAnnotation("@ColumnType(jdbcType=" + jdbcTypeName + ")");
					}
			}
		}
	}

	private void addClassComment(TopLevelClass topLevelClass) {
		topLevelClass.addFormattedJavadoc(new StringBuilder("实体类请不要修改，如有改变请从数据库重新生成"), 0);
	}

	private void addFieldEnum(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		if (this.modelFieldEnum) {
			String enumName = "FieldEnum";
			String javaFieldName = "javaFieldName";
			String dbFieldName = "dbFieldName";
			InnerEnum enum1 = new InnerEnum(new FullyQualifiedJavaType(enumName));
			enum1.setVisibility(JavaVisibility.PUBLIC);

			StringBuilder enumConstant = new StringBuilder();
			List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
			int index = 0;
			for (IntrospectedColumn column : allColumns) {
				String dbName = column.getActualColumnName();
				String javaName = column.getJavaProperty();
				enumConstant.append(dbName.toUpperCase()).append("(\"").append(javaName).append("\",\"").append(dbName).append("\")");
				if (++index < allColumns.size()) {
					enumConstant.append(",\n\t\t");
				}
			}
			enum1.addEnumConstant(enumConstant.toString());
			
			//java字段
			Field field = new Field();
			field.setVisibility(JavaVisibility.PRIVATE);
			field.setStatic(false);
			field.setType(new FullyQualifiedJavaType("String"));
			field.setName(javaFieldName);
			enum1.addField(field);
			//db字段
			Field field1 = new Field();
			field1.setVisibility(JavaVisibility.PRIVATE);
			field1.setStatic(false);
			field1.setType(new FullyQualifiedJavaType("String"));
			field1.setName(dbFieldName);
			enum1.addField(field1);
			
			//构造器
			Method method = new Method();
			method.setConstructor(true);
			method.setVisibility(JavaVisibility.DEFAULT);
			method.setStatic(false);
			method.setName(enumName);
			method.addParameter(new Parameter(new FullyQualifiedJavaType("String"), javaFieldName));
			method.addParameter(new Parameter(new FullyQualifiedJavaType("String"), dbFieldName));
			method.addBodyLine("this."+javaFieldName+" = "+javaFieldName+";");
			method.addBodyLine("this."+dbFieldName+" = "+dbFieldName+";");
			enum1.addMethod(method);
			
			
			//方法
			Method getMethod = new Method();
			getMethod.setConstructor(false);
			getMethod.setVisibility(JavaVisibility.PUBLIC);
			getMethod.setStatic(false);
			getMethod.setName(javaFieldName);
			getMethod.addBodyLine("return "+javaFieldName+";");
			getMethod.setReturnType(new FullyQualifiedJavaType("String"));
			enum1.addMethod(getMethod);
			
			Method getMethod1 = new Method();
			getMethod1.setConstructor(false);
			getMethod1.setVisibility(JavaVisibility.PUBLIC);
			getMethod1.setStatic(false);
			getMethod1.setName(dbFieldName);
			getMethod1.addBodyLine("return "+dbFieldName+";");
			getMethod1.setReturnType(new FullyQualifiedJavaType("String"));
			enum1.addMethod(getMethod1);
			
			topLevelClass.addInnerEnum(enum1);
		}
	}

	public static void main(String[] args) {
	}
}