(this.webpackJsonperpclient=this.webpackJsonperpclient||[]).push([[15],{332:function(e,t,a){"use strict";a.d(t,"c",(function(){return c})),a.d(t,"b",(function(){return r})),a.d(t,"a",(function(){return i}));var n=a(40),c=function(){return{type:n.e}},r=function(){return{type:n.b}},i=function(e){return{type:n.c,formData:e}}},334:function(e,t,a){"use strict";a.d(t,"a",(function(){return j}));var n=a(68),c=a(0),r=a.n(c),i=a(729),s=a(724),l=(a(110),a(93)),d=a(94),o=a(1),j=r.a.memo((function(e){var t=e.data,a=e.handlRowClick,c=Object(n.a)(e,["data","handlRowClick"]);return Object(o.jsxs)("div",{className:"grid-action-icon",children:[c.view&&Object(o.jsx)(i.a,{overlay:Object(o.jsx)(s.a,{id:"tooltip-view",children:"View"}),children:Object(o.jsx)(l.a,{icon:d.d,onClick:function(){return a(t,"view")}})}),c.edit&&Object(o.jsx)(i.a,{overlay:Object(o.jsx)(s.a,{id:"tooltip-edit",children:"Edit"}),children:Object(o.jsx)(l.a,{icon:d.c,onClick:function(){return a(t,"edit")}})}),c.list&&Object(o.jsx)(i.a,{overlay:Object(o.jsx)(s.a,{id:"tooltip-list",children:"List"}),children:Object(o.jsx)(l.a,{icon:d.f,onClick:function(){return a(t,"list")}})}),c.delete&&Object(o.jsx)(i.a,{overlay:Object(o.jsx)(s.a,{id:"tooltip-delete",children:"Delete"}),children:Object(o.jsx)(l.a,{icon:d.g,onClick:function(){return a(t,"delete")}})})]})}))},750:function(e,t,a){"use strict";a.r(t);var n=a(348),c=a.n(n),r=a(349),i=a(16),s=a(11),l=a(0),d=a(20),o=a(343),j=a.n(o),u=a(317),b=a(318),m=a(731),h=a(110),O=a(334),p=a(344),x=a(49),f=a(69),v=a(332),y=a(17),g=a(95),k=a(1),N=a(112);t.default=function(){var e=Object(d.c)(),t=Object(l.useState)({left:8,right:4}),a=Object(i.a)(t,1)[0],n=Object(l.useState)(null),o=Object(i.a)(n,2),w=o[0],C=o[1],S=Object(l.useState)(!1),F=Object(i.a)(S,2),R=F[0],D=F[1],z=Object(l.useState)({}),P=Object(i.a)(z,2),V=P[0],q=P[1],B=[{name:"id",header:"S.No",minWidth:50,defaultFlex:1},{name:"name",header:"Department Name",maxWidth:200,defaultFlex:1},{name:"type",header:"Department Type",maxWidth:200,defaultFlex:1},{name:"",header:"Action",defaultFlex:1,sortable:!1,render:function(e){var t=e.data;return Object(k.jsx)(O.a,{data:t,handlRowClick:E,edit:!0})}}],E=Object(l.useCallback)((function(t,a){e(Object(v.a)(t)),"edit"===a&&(D(!0),q(t))}),[]),H=Object(l.useCallback)((function(e){e.skip,e.limit,e.sortInfo,e.groupBy;var t=e.filterValue;return new Promise((function(e,a){null!==t&&t[0].value;Object(f.g)().then((function(t){return e({count:t.data.response.numberOfElements,data:t.data.response})})).catch((function(t){return e({count:0,data:[]})}))}))}),[]),T=Object(l.useState)({name:"",type:"",active:""}),W=Object(i.a)(T,1)[0],A=Object(l.useState)({}),J=Object(i.a)(A,2),L=J[0],I=J[1];Object(l.useEffect)((function(){R&&I(V)}),[R,V]);var U=function(){var t=Object(r.a)(c.a.mark((function t(a,n){var r,i,s,l;return c.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(r=n.resetForm,i={},a.name){t.next=5;break}return i.name=alert("Department Name is required"),t.abrupt("return",!1);case 5:if(a.type){t.next=8;break}return i.type=alert("Department Code is required"),t.abrupt("return",!1);case 8:return t.prev=8,e(Object(v.c)()),t.next=12,y.a.Request(void 0===a.id?"POST":"PUT","http://dev-erp1.mnit.ac.in:8080/department",a);case 12:s=t.sent,c=s,w.current.reload(),console.log("Recored added, append to list",c),e(Object(v.b)()),g.a.success(s.data.message),r({initialValues:W}),t.next=24;break;case 19:t.prev=19,t.t0=t.catch(8),e(Object(v.b)()),l=void 0===t.t0.message?t.t0:t.t0.message,g.a.error(l);case 24:case"end":return t.stop()}var c}),t,null,[[8,19]])})));return function(e,a){return t.apply(this,arguments)}}();return Object(k.jsxs)(k.Fragment,{children:[Object(k.jsx)("div",{className:"container",children:Object(k.jsx)("nav",{"aria-label":"breadcrumb",children:Object(k.jsxs)("ol",{className:"breadcrumb",children:[Object(k.jsx)("li",{className:"theme-font-size breadcrumb-item",children:Object(k.jsx)(s.b,{className:"theme-color",to:"/dashboard",children:N.home})}),Object(k.jsx)("li",{className:"theme-font-size breadcrumb-item active","aria-current":"page",children:N.department})]})})}),Object(k.jsxs)(u.a,{children:[Object(k.jsx)(b.a,{md:{span:a.left},children:Object(k.jsxs)(m.a,{children:[Object(k.jsx)(m.a.Header,{children:N.tableRecords}),Object(k.jsx)(m.a.Body,{children:Object(k.jsx)(j.a,{onReady:C,idProperty:"id",style:{minHeight:400},columns:B,dataSource:H})})]})}),Object(k.jsx)(b.a,{md:{span:a.right},children:Object(k.jsxs)(m.a,{children:[Object(k.jsx)(m.a.Header,{children:N.departmentForm}),Object(k.jsx)(m.a.Body,{children:Object(k.jsx)("div",{children:Object(k.jsx)(p.a,{dataLength:60,height:400,children:Object(k.jsx)(x.c,{initialValues:R?L:W,enableReinitialize:!0,onSubmit:U,children:function(e){return Object(k.jsxs)(x.b,{children:[Object(k.jsxs)("div",{className:"form-group",children:[Object(k.jsx)("label",{className:"theme-font-size",htmlFor:"name",children:N.departmentname}),Object(k.jsx)(x.a,{id:"name",className:"form-control",name:"name",placeholder:"Department Name"})]}),Object(k.jsxs)("div",{className:"form-group",children:[Object(k.jsx)("label",{className:"theme-font-size",htmlFor:"type",children:N.departmentcode}),Object(k.jsxs)(x.a,{as:"select",name:"type",id:"type",className:"form-control",children:[Object(k.jsx)("option",{value:"",children:"---"}),Object(k.jsx)("option",{value:"A",children:N.academics}),Object(k.jsx)("option",{value:"O",children:N.organization})]})]}),Object(k.jsx)(h.a,{className:"theme-back-color",variant:"primary",type:"submit",children:N.save})]})}})})})})]})})]})]})}}}]);
//# sourceMappingURL=15.85e70b1d.chunk.js.map