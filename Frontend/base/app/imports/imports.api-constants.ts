

export class ImportsApiConstants {
    public static readonly create: any = {
        url: '/rest/rappitimports/',
        method: 'POST',
        showloading: true
    };
    public static readonly getErrorData: any = {
        url: '/rest/rappitimporterrors/{sid}',
        method: 'POST',
        showloading: true
    };
    public static readonly getDownloadurl: any = {
        url: '/rest/getDownloadurl/',
        method: 'POST',
        showloading: true
    };
    public static readonly update: any = {
        url: '/rest/rappitimports/validateTemplate/',
        method: 'PUT',
        showloading: true
    };

}
