package sk.mrtn.library.client.utils;

import com.google.gwt.core.client.JavaScriptObject;
import elemental.client.Browser;
import elemental.css.CSSStyleDeclaration;
import elemental.dom.Element;
import elemental.dom.Node;
import elemental.html.DivElement;
import sk.mrtn.library.client.ui.mainpanel.IResponsivePanel;

/**
 * Created by martinliptak on 02/11/2016.
 */
public class Spinner implements IResponsivePanel {

    private static class SpinnerImpl extends JavaScriptObject {
        protected SpinnerImpl() {
        }

        public final native void spin(final Element parent) /*-{
            this.spin(parent);
        }-*/;

        public final native void stop() /*-{
            this.stop();
        }-*/;

        public final native String getColor() /*-{
            return this.options.color;
        }-*/;
    }

    private static class SpinnerImplFactory extends JavaScriptObject {

        private static SpinnerImplFactory instance;

        public static SpinnerImpl createSpinner() {
            return createSpinner(createDefaultOptions());
        }

        public static SpinnerImpl createSpinner(final String color) {
            return createSpinner(createOptions(color, createDefaultOptions()));
        }

        public static SpinnerImpl createSpinner(final JavaScriptObject options) {
            if (instance == null) {
                instance = createFactory();
            }

            return instance.newSpinner(options);
        }

        private static native SpinnerImplFactory createFactory() /*-{
            var spinnerFactory = {};
            var spinnerConstructor = function() {
                // http://spin.js.org/#v2.3.2 global value document replaced by $doc
                !function(a,b){"object"==typeof module&&module.exports?module.exports=b():"function"==typeof define&&define.amd?define(b):a.Spinner=b()}(this,function(){"use strict";function a(a,b){var c,d=$doc.createElement(a||"div");for(c in b)d[c]=b[c];return d}function b(a){for(var b=1,c=arguments.length;c>b;b++)a.appendChild(arguments[b]);return a}function c(a,b,c,d){var e=["opacity",b,~~(100*a),c,d].join("-"),f=.01+c/d*100,g=Math.max(1-(1-a)/b*(100-f),a),h=j.substring(0,j.indexOf("Animation")).toLowerCase(),i=h&&"-"+h+"-"||"";return m[e]||(k.insertRule("@"+i+"keyframes "+e+"{0%{opacity:"+g+"}"+f+"%{opacity:"+a+"}"+(f+.01)+"%{opacity:1}"+(f+b)%100+"%{opacity:"+a+"}100%{opacity:"+g+"}}",k.cssRules.length),m[e]=1),e}function d(a,b){var c,d,e=a.style;if(b=b.charAt(0).toUpperCase()+b.slice(1),void 0!==e[b])return b;for(d=0;d<l.length;d++)if(c=l[d]+b,void 0!==e[c])return c}function e(a,b){for(var c in b)a.style[d(a,c)||c]=b[c];return a}function f(a){for(var b=1;b<arguments.length;b++){var c=arguments[b];for(var d in c)void 0===a[d]&&(a[d]=c[d])}return a}function g(a,b){return"string"==typeof a?a:a[b%a.length]}function h(a){this.opts=f(a||{},h.defaults,n)}function i(){function c(b,c){return a("<"+b+' xmlns="urn:schemas-microsoft.com:vml" class="spin-vml">',c)}k.addRule(".spin-vml","behavior:url(#default#VML)"),h.prototype.lines=function(a,d){function f(){return e(c("group",{coordsize:k+" "+k,coordorigin:-j+" "+-j}),{width:k,height:k})}function h(a,h,i){b(m,b(e(f(),{rotation:360/d.lines*a+"deg",left:~~h}),b(e(c("roundrect",{arcsize:d.corners}),{width:j,height:d.scale*d.width,left:d.scale*d.radius,top:-d.scale*d.width>>1,filter:i}),c("fill",{color:g(d.color,a),opacity:d.opacity}),c("stroke",{opacity:0}))))}var i,j=d.scale*(d.length+d.width),k=2*d.scale*j,l=-(d.width+d.length)*d.scale*2+"px",m=e(f(),{position:"absolute",top:l,left:l});if(d.shadow)for(i=1;i<=d.lines;i++)h(i,-2,"progid:DXImageTransform.Microsoft.Blur(pixelradius=2,makeshadow=1,shadowopacity=.3)");for(i=1;i<=d.lines;i++)h(i);return b(a,m)},h.prototype.opacity=function(a,b,c,d){var e=a.firstChild;d=d.shadow&&d.lines||0,e&&b+d<e.childNodes.length&&(e=e.childNodes[b+d],e=e&&e.firstChild,e=e&&e.firstChild,e&&(e.opacity=c))}}var j,k,l=["webkit","Moz","ms","O"],m={},n={lines:12,length:7,width:5,radius:10,scale:1,corners:1,color:"#000",opacity:.25,rotate:0,direction:1,speed:1,trail:100,fps:20,zIndex:2e9,className:"spinner",top:"50%",left:"50%",shadow:!1,hwaccel:!1,position:"absolute"};if(h.defaults={},f(h.prototype,{spin:function(b){this.stop();var c=this,d=c.opts,f=c.el=a(null,{className:d.className});if(e(f,{position:d.position,width:0,zIndex:d.zIndex,left:d.left,top:d.top}),b&&b.insertBefore(f,b.firstChild||null),f.setAttribute("role","progressbar"),c.lines(f,c.opts),!j){var g,h=0,i=(d.lines-1)*(1-d.direction)/2,k=d.fps,l=k/d.speed,m=(1-d.opacity)/(l*d.trail/100),n=l/d.lines;!function o(){h++;for(var a=0;a<d.lines;a++)g=Math.max(1-(h+(d.lines-a)*n)%l*m,d.opacity),c.opacity(f,a*d.direction+i,g,d);c.timeout=c.el&&setTimeout(o,~~(1e3/k))}()}return c},stop:function(){var a=this.el;return a&&(clearTimeout(this.timeout),a.parentNode&&a.parentNode.removeChild(a),this.el=void 0),this},lines:function(d,f){function h(b,c){return e(a(),{position:"absolute",width:f.scale*(f.length+f.width)+"px",height:f.scale*f.width+"px",background:b,boxShadow:c,transformOrigin:"left",transform:"rotate("+~~(360/f.lines*k+f.rotate)+"deg) translate("+f.scale*f.radius+"px,0)",borderRadius:(f.corners*f.scale*f.width>>1)+"px"})}for(var i,k=0,l=(f.lines-1)*(1-f.direction)/2;k<f.lines;k++)i=e(a(),{position:"absolute",top:1+~(f.scale*f.width/2)+"px",transform:f.hwaccel?"translate3d(0,0,0)":"",opacity:f.opacity,animation:j&&c(f.opacity,f.trail,l+k*f.direction,f.lines)+" "+1/f.speed+"s linear infinite"}),f.shadow&&b(i,e(h("#000","0 0 4px #000"),{top:"2px"})),b(d,b(i,h(g(f.color,k),"0 0 1px rgba(0,0,0,.1)")));return d},opacity:function(a,b,c){b<a.childNodes.length&&(a.childNodes[b].style.opacity=c)}}),"undefined"!=typeof $doc){k=function(){var c=a("style",{type:"text/css"});return b($doc.getElementsByTagName("head")[0],c),c.sheet||c.styleSheet}();var o=e(a("group"),{behavior:"url(#default#VML)"});!d(o,"transform")&&o.adj?i():j=d(o,"animation")}return h});
            };
            spinnerConstructor.call(spinnerFactory);
            return spinnerFactory;
        }-*/;

        private static native JavaScriptObject createDefaultOptions() /*-{
            return {
                lines: 12, // The number of lines to draw
                length: 6, // The length of each line
                width: 2, // The line thickness
                radius: 7, // The radius of the inner circle
                corners: 0, // Corner roundness (0..1)
                rotate: 0, // The rotation offset
                direction: 1, // 1: clockwise, -1: counterclockwise
                color: "#fff", // #rgb or #rrggbb or array of colors
                speed: 1, // Rounds per second
                trail: 60, // Afterglow percentage
                shadow: false, // Whether to render a shadow
                hwaccel: false, // Whether to use hardware acceleration
                className: 'spinner', // The CSS class to assign to the spinner
                position: 'relative',
                zIndex:0
            };
        }-*/;

        private static native JavaScriptObject createOptions(
                final String color,
                final JavaScriptObject defaultOptions) /*-{
            defaultOptions.color = color;
            return defaultOptions;
        }-*/;

        protected SpinnerImplFactory() {
        }

        public final native SpinnerImpl newSpinner(final JavaScriptObject options) /*-{
            var s = new this.Spinner(options);
            s.options = options;
            return s;
        }-*/;
    }

    private final JavaScriptObject options;
    private String color;
    private SpinnerImpl impl;

    private DivElement divElement;

    public Spinner(
    ){
        this.options = null;
    }

    @Override
    public void onResized(double width, double height) {

    }

    @Override
    public Node asNode() {
        return getWrapper();
    }

    /**
     * Sets the color of spinner.
     *
     * @param color color name, #rgb or #rrggbb or array of colors in javascript syntax.
     */
    public void setColor(final String color) {
        this.color = color;
    }


    public DivElement getWrapper() {
        if (this.divElement == null) {
            this.divElement = Browser.getDocument().createDivElement();
            this.divElement.getStyle().setBackgroundColor("#000");
            this.divElement.getStyle().setPosition(CSSStyleDeclaration.Position.ABSOLUTE);
            this.divElement.getStyle().setTop(0, CSSStyleDeclaration.Unit.PX);
            this.divElement.getStyle().setLeft(0, CSSStyleDeclaration.Unit.PX);
            this.divElement.getStyle().setWidth(100, CSSStyleDeclaration.Unit.PCT);
            this.divElement.getStyle().setHeight(100, CSSStyleDeclaration.Unit.PCT);
            this.divElement.setId("spiner");
        }
        return this.divElement;
    }

    public void onLoad() {
        if (this.impl == null || this.impl.getColor() != this.color) {
            if (this.options == null) {
                this.impl = SpinnerImplFactory.createSpinner(color);
            } else {
                this.impl = SpinnerImplFactory.createSpinner(SpinnerImplFactory.createOptions(color, this.options));
            }
        }
        this.impl.spin(getWrapper());
    }

    public void onUnload() {
        this.impl.stop();
    }
}
