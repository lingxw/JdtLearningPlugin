package ling.learning.jdt.astvisitor;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.ui.JavaElementLabels;

public class TypeResoveVisitor extends ASTVisitor {
	
	private static final long LABEL_OPTIONS=
			JavaElementLabels.F_APP_TYPE_SIGNATURE | JavaElementLabels.M_PARAMETER_TYPES |
			JavaElementLabels.M_APP_RETURNTYPE | JavaElementLabels.ALL_FULLY_QUALIFIED |
			JavaElementLabels.T_TYPE_PARAMETERS |JavaElementLabels.USE_RESOLVED;
	
	public boolean visit(SimpleType node){
		String line = "";

		if(node.getRoot() instanceof CompilationUnit) {
			CompilationUnit cu = (CompilationUnit)node.getRoot();
			line = String.format(
					"%s[%s+%s] (%s,%s)"
					, node.getClass().getSimpleName()
					, node.getStartPosition()
					, node.getLength()
					, cu.getLineNumber(node.getStartPosition())
					, cu.getColumnNumber(node.getStartPosition())
					);
			System.out.println(line);
		} else {
			line = String.format(
					"%s[%s+%s] (?,?)"
					, node.getClass().getSimpleName()
					, node.getStartPosition()
					, node.getLength()
					);
			System.out.println(line);
		}
		
		ITypeBinding binding = node.resolveBinding();
		if (binding != null) {
			line = String.format(
					" > type binding name:%s"
					, binding.getName()
					);
			System.out.println(line);
			line = String.format(
					" > type binding qualified name:%s"
					, binding.getQualifiedName()
					);
			System.out.println(line);
			line = String.format(
					" > type binding binary name:%s"
					, binding.getBinaryName()
					);
			System.out.println(line);
			line = String.format(
					" > type binding key:%s"
					, binding.getKey()
					);
			System.out.println(line);

			IJavaElement element = binding.getJavaElement();
			if(element != null) {
				line = String.format(
						" > type binding jar path:%s"
						, element.getPath()
						);
				System.out.println(line);
				element = element.getParent();
				if(element != null) {
					line = String.format(
							" > type binding class name:%s"
							, element.getElementName()
							);
					System.out.println(line);
				}
			}
		}

		return true;
	}
	
	public boolean visit(SimpleName node){
		String line = "";

		if(node.getRoot() instanceof CompilationUnit) {
			CompilationUnit cu = (CompilationUnit)node.getRoot();
			line = String.format(
					"%s[%s+%s] (%s,%s)"
					, node.getClass().getSimpleName()
					, node.getStartPosition()
					, node.getLength()
					, cu.getLineNumber(node.getStartPosition())
					, cu.getColumnNumber(node.getStartPosition())
					);
			System.out.println(line);
		} else {
			line = String.format(
					"%s[%s+%s] (?,?)"
					, node.getClass().getSimpleName()
					, node.getStartPosition()
					, node.getLength()
					);
			System.out.println(line);
		}
		
		IBinding binding = node.resolveBinding();
		if (binding != null && binding.getKind() == IBinding.VARIABLE) {
			line = String.format(
					" > variable binding name:%s"
					, binding.getName()
					);
			System.out.println(line);
			line = String.format(
					" > variable binding key:%s"
					, binding.getKey()
					);
			System.out.println(line);
			IJavaElement element = binding.getJavaElement();
			if(element != null)
			{
				String classname= element.getClass().getName();
				line = String.format(
						" > %s:%s"
						, classname.substring(classname.lastIndexOf('.') + 1)
						, JavaElementLabels.getElementLabel(element, LABEL_OPTIONS)
						);
				System.out.println(line);
			}
		}

		return true;
	}
	
	public boolean visit(MethodInvocation node){
		String line = "";

		if(node.getRoot() instanceof CompilationUnit) {
			CompilationUnit cu = (CompilationUnit)node.getRoot();
			line = String.format(
					"%s[%s+%s] (%s,%s)"
					, node.getClass().getSimpleName()
					, node.getStartPosition()
					, node.getLength()
					, cu.getLineNumber(node.getStartPosition())
					, cu.getColumnNumber(node.getStartPosition())
					);
			System.out.println(line);
		} else {
			line = String.format(
					"%s[%s+%s] (?,?)"
					, node.getClass().getSimpleName()
					, node.getStartPosition()
					, node.getLength()
					);
			System.out.println(line);
		}
		
		IMethodBinding binding = node.resolveMethodBinding();
		if (binding != null) {
			line = String.format(
					" > method binding name:%s"
					, binding.getName()
					);
			System.out.println(line);
			line = String.format(
					" > method binding key:%s"
					, binding.getKey()
					);
			System.out.println(line);
			line = String.format(
					" > method binding is recovered:%s"
					, binding.isRecovered()
					);
			System.out.println(line);
			
			IJavaElement element = binding.getJavaElement();
			if(element != null)
			{
				String classname= element.getClass().getName();
				line = String.format(
						" > %s:%s"
						, classname.substring(classname.lastIndexOf('.') + 1)
						, JavaElementLabels.getElementLabel(element, LABEL_OPTIONS)
						);
				System.out.println(line);
			}
		}

		return true;
	}
}
