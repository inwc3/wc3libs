package net.moonlightflower.wc3libs.txt.app.jass;

import net.moonlightflower.wc3libs.antlr.JassLexer;
import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.antlr.LightJassParser;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class FuncDecl implements Decl {
    public final static String CONFIG_NAME = "config";
    public final static String INIT_ALLY_PRIORITIES = "InitAllyPriorities";
    public final static String INIT_CUSTOM_PLAYER_SLOTS = "InitCustomPlayerSlots";
    public final static String INIT_CUSTOM_TEAMS = "InitCustomTeams";
    public final static String MAIN_NAME = "main";

    private boolean _isConstant;
    private String _name;
    private List<Param> _params;
    private String _returnType;
    private boolean _isNative;

    public String getName() {
        return _name;
    }

    public FuncDecl(boolean isConstant, @Nonnull String name, List<Param> params, @Nullable String returnType, boolean isNative) {
        _isConstant = isConstant;
        _name = name;
        _params = params;
        _returnType = returnType;
        _isNative = isNative;
    }

    public FuncDecl(boolean isConstant, @Nonnull String name, List<Param> params, @Nullable String returnType) {
        this(isConstant, name, params, returnType, false);
    }

    public static FuncDecl create(@Nonnull LightJassParser.Native_declContext native_declContext) {
        List<Param> params = new ArrayList<>();

        LightJassParser.Func_param_listContext paramList = native_declContext.func_param_list();

        for (LightJassParser.Func_paramContext funcParamContext : paramList.func_param()) {
            params.add(Param.create(funcParamContext));
        }

        return new FuncDecl(native_declContext.CONST_DECL() != null, native_declContext.func_name().getText(), params, native_declContext.func_return_type().getText(), true);
    }

    public static FuncDecl create(@Nonnull LightJassParser.Func_declContext func_declContext) {
        List<Param> params = new ArrayList<>();

        LightJassParser.Func_param_listContext paramList = func_declContext.func_param_list();

        for (LightJassParser.Func_paramContext funcParamContext : paramList.func_param()) {
            params.add(Param.create(funcParamContext));
        }

        return new FuncDecl(func_declContext.CONST_DECL() != null, func_declContext.func_name().getText(), params, func_declContext.func_return_type().getText(), false);
    }

    public void write(@Nonnull StringWriter sw) {
        if (_isConstant) sw.write(JassScript.getPrimaryLiteral(JassLexer.CONST_DECL) + " ");

        if (_isNative) {
            sw.write(JassScript.getPrimaryLiteral(JassLexer.NATIVE));
        } else {
            sw.write(JassScript.getPrimaryLiteral(JassLexer.FUNCTION));
        }

        sw.write(" ");

        sw.write(_name);

        sw.write(" ");

        sw.write(JassScript.getPrimaryLiteral(JassLexer.TAKES));

        sw.write(" ");

        if (_params.isEmpty()) {
            sw.write(JassScript.getPrimaryLiteral(JassLexer.NOTHING));
        } else {
            boolean first = true;

            for (Param param : _params) {
                if (first) {
                    first = false;
                } else {
                    sw.write(JassScript.getPrimaryLiteral(JassLexer.COMMA) + " ");
                }

                param.write(sw);
            }
        }

        sw.write(" ");

        sw.write(JassScript.getPrimaryLiteral(JassLexer.RETURNS));

        sw.write(" ");

        sw.write(_returnType == null ? JassScript.getPrimaryLiteral(JassLexer.NOTHING) : _returnType);
    }
}
