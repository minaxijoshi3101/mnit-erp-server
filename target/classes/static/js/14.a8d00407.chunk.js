(this.webpackJsonperpclient=this.webpackJsonperpclient||[]).push([[14],{332:function(e,t,a){"use strict";a.d(t,"c",(function(){return c})),a.d(t,"b",(function(){return n})),a.d(t,"a",(function(){return i}));var r=a(40),c=function(){return{type:r.e}},n=function(){return{type:r.b}},i=function(e){return{type:r.c,formData:e}}},334:function(e,t,a){"use strict";a.d(t,"a",(function(){return b}));var r=a(68),c=a(0),n=a.n(c),i=a(729),s=a(724),l=(a(110),a(93)),d=a(94),o=a(1),b=n.a.memo((function(e){var t=e.data,a=e.handlRowClick,c=Object(r.a)(e,["data","handlRowClick"]);return Object(o.jsxs)("div",{className:"grid-action-icon",children:[c.view&&Object(o.jsx)(i.a,{overlay:Object(o.jsx)(s.a,{id:"tooltip-view",children:"View"}),children:Object(o.jsx)(l.a,{icon:d.d,onClick:function(){return a(t,"view")}})}),c.edit&&Object(o.jsx)(i.a,{overlay:Object(o.jsx)(s.a,{id:"tooltip-edit",children:"Edit"}),children:Object(o.jsx)(l.a,{icon:d.c,onClick:function(){return a(t,"edit")}})}),c.list&&Object(o.jsx)(i.a,{overlay:Object(o.jsx)(s.a,{id:"tooltip-list",children:"List"}),children:Object(o.jsx)(l.a,{icon:d.f,onClick:function(){return a(t,"list")}})}),c.delete&&Object(o.jsx)(i.a,{overlay:Object(o.jsx)(s.a,{id:"tooltip-delete",children:"Delete"}),children:Object(o.jsx)(l.a,{icon:d.g,onClick:function(){return a(t,"delete")}})})]})}))},756:function(e,t,a){"use strict";a.r(t);var r=a(348),c=a.n(r),n=a(349),i=a(16),s=a(11),l=a(0),d=a(20),o=a(343),b=a.n(o),j=a(317),u=a(318),m=a(731),h=a(110),O=a(334),f=a(344),x=a(49),p=a(69),v=a(332),g=a(17),k=a(95),N=a(1),y=a(112);t.default=function(){var e=Object(d.c)(),t=Object(l.useState)({left:8,right:4}),a=Object(i.a)(t,1)[0],r=Object(l.useState)(null),o=Object(i.a)(r,2),w=o[0],C=o[1],S=Object(l.useState)(!1),F=Object(i.a)(S,2),D=F[0],R=F[1],z=Object(l.useState)({}),P=Object(i.a)(z,2),V=P[0],q=P[1],B=[{name:"id",header:"S.No",minWidth:50,defaultFlex:1},{name:"name",header:"Degree Name",maxWidth:200,defaultFlex:1},{name:"abbreviation",header:"Degree Code",maxWidth:200,defaultFlex:1},{name:"",header:"Action",defaultFlex:1,sortable:!1,render:function(e){var t=e.data;return Object(N.jsx)(O.a,{data:t,handlRowClick:E,edit:!0})}}],E=Object(l.useCallback)((function(t,a){e(Object(v.a)(t)),"edit"===a&&(R(!0),q(t))}),[]),H=Object(l.useCallback)((function(e){e.skip,e.limit,e.sortInfo,e.groupBy;var t=e.filterValue;return new Promise((function(e,a){null!==t&&t[0].value;Object(p.f)().then((function(t){return e({count:t.data.response.numberOfElements,data:t.data.response})})).catch((function(t){return e({count:0,data:[]})}))}))}),[]),W=Object(l.useState)({name:"",abbreviation:""}),J=Object(i.a)(W,1)[0],L=Object(l.useState)({}),T=Object(i.a)(L,2),A=T[0],I=T[1];Object(l.useEffect)((function(){D&&I(V)}),[D,V]);var U=function(){var t=Object(n.a)(c.a.mark((function t(a,r){var n,i,s,l;return c.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(n=r.resetForm,i={},a.name){t.next=5;break}return i.name=alert("Degree Name is required"),t.abrupt("return",!1);case 5:if(a.abbreviation){t.next=8;break}return i.abbreviation=alert("Degree Code is required"),t.abrupt("return",!1);case 8:return t.prev=8,e(Object(v.c)()),t.next=12,g.a.Request(void 0===a.id?"POST":"PUT","http://dev-erp1.mnit.ac.in:8080/degree",a);case 12:s=t.sent,c=s,w.current.reload(),console.log("Recored added, append to list",c),e(Object(v.b)()),k.a.success(s.data.message),n({initialValues:J}),t.next=24;break;case 19:t.prev=19,t.t0=t.catch(8),e(Object(v.b)()),l=void 0===t.t0.message?t.t0:t.t0.message,k.a.error(l);case 24:case"end":return t.stop()}var c}),t,null,[[8,19]])})));return function(e,a){return t.apply(this,arguments)}}();return Object(N.jsxs)(N.Fragment,{children:[Object(N.jsx)("div",{className:"container",children:Object(N.jsx)("nav",{"aria-label":"breadcrumb",children:Object(N.jsxs)("ol",{className:"breadcrumb",children:[Object(N.jsx)("li",{className:"theme-font-size breadcrumb-item",children:Object(N.jsx)(s.b,{className:"theme-color",to:"/dashboard",children:y.home})}),Object(N.jsx)("li",{className:"theme-font-size breadcrumb-item active","aria-current":"page",children:y.degree})]})})}),Object(N.jsxs)(j.a,{children:[Object(N.jsx)(u.a,{md:{span:a.left},children:Object(N.jsxs)(m.a,{children:[Object(N.jsx)(m.a.Header,{children:y.tableRecords}),Object(N.jsx)(m.a.Body,{children:Object(N.jsx)(b.a,{onReady:C,idProperty:"id",style:{minHeight:400},columns:B,dataSource:H})})]})}),Object(N.jsx)(u.a,{md:{span:a.right},children:Object(N.jsxs)(m.a,{children:[Object(N.jsx)(m.a.Header,{children:y.degreeForm}),Object(N.jsx)(m.a.Body,{children:Object(N.jsx)("div",{children:Object(N.jsx)(f.a,{dataLength:60,height:400,children:Object(N.jsx)(x.c,{initialValues:D?A:J,enableReinitialize:!0,onSubmit:U,children:function(e){return Object(N.jsxs)(x.b,{children:[Object(N.jsxs)("div",{className:"form-group",children:[Object(N.jsx)("label",{className:"theme-font-size",htmlFor:"name",children:y.degreename}),Object(N.jsx)(x.a,{id:"name",className:"form-control",name:"name",placeholder:"Degree Name"})]}),Object(N.jsxs)("div",{className:"form-group",children:[Object(N.jsx)("label",{className:"theme-font-size",htmlFor:"abbreviation",children:y.degreecode}),Object(N.jsx)(x.a,{id:"abbreviation",className:"form-control",name:"abbreviation",placeholder:"Degree Code"})]}),Object(N.jsx)(h.a,{className:"theme-back-color",variant:"primary",type:"submit",children:y.save})]})}})})})})]})})]})]})}}}]);
//# sourceMappingURL=14.a8d00407.chunk.js.map